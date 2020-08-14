function sendAJAXRequest(receiveData, params) {
    var req = new XMLHttpRequest();
    req.onreadystatechange = getReadyStateHandler(req, receiveData);
    req.open('POST', '?op=web&' + params, true);
    req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
    req.send(formToString('appForm')); //TODO: Send only the relevant data by using path.
}

/**
 * Update document with new data.
 *
 * @param response
 */
function receiveData(response) {
    document.getElementById('appForm').innerHTML = response.responseText;
    document.getElementById("action-remover").style.display = "none";
    // Focus on last active element
    //document.getElementById(localStorage.activeElement).focus();
}

function getReadyStateHandler(req, responseHandler) {
    // Return an anonymous function that listens to the XMLHttpRequest instance
    return function () {
        // If the request's status is "complete"
        if (req.readyState == 4) {
            // Check that we received a successful response from the server
            if (req.status == 200) {
                // Pass the XML payload of the response to the handler function.
                responseHandler(req); // responseXML or responseText further
            } else {
                // An HTTP problem has occurred
                alert("HTTP error " + req.status + ": " + req.statusText);
            }
        }
    }
}

/* converts most of input controls into a query string - starts from "&"! */
function formToString(formName) {
    var obj = document.forms[formName];
    var getstr = "", i, j;
    for (i = 0; i < obj.getElementsByTagName("input").length; i++) {
        var elem = obj.getElementsByTagName("input")[i];
        var type = elem.type.toLowerCase();
        if (type == "text" || type == "hidden" || type == "password") {
            getstr += "&" + elem.name + "=" + escape2(elem.value);
        }
        else if (type == "checkbox") {
            if (elem.checked)
                getstr += "&" + elem.name + "=" + escape2(elem.value);
            else
                getstr += "&" + elem.name + "=";
        }
        else if (type == "radio") {
            if (elem.checked)
                getstr += "&" + elem.name + "=" + escape2(elem.value);
        }
    }
    for (i = 0; i < obj.getElementsByTagName("select").length; i++) {
        var elem = obj.getElementsByTagName("select")[i];
        for (j = 0; j < elem.options.length; j++) {
            if (elem.options[j].selected)
                getstr += "&" + elem.name + "=" + escape2(elem.options[j].value);
        }
    }
    for (i = 0; i < obj.getElementsByTagName("textarea").length; i++) {
        var elem = obj.getElementsByTagName("textarea")[i];
        getstr += "&" + elem.name + "=" + escape2(elem.value);
    }
    return getstr;
}

/* function to escape query string correctly */
function escape2(str0) {
    //  not encoded by escape: * @ - _ + . /
    var str = encodeURI(str0);

    str = str.replace(/\x2F/g, "%2F"); // /
    str = str.replace(/\x2B/g, "%2B"); // +
    str = str.replace(/\x26/g, "%26"); // &

    return str;
}

/**
 * Input click event.
 *
 * @param event
 * @param id
 * @param path
 */
function actionPerformedActionEvent(event, id, path) {
    fireEvent(path, id, "actionPerformed-ActionEvent");
}

/**
 * Table row selection event.
 *
 * @param ida
 * @param path
 */
function valueChangedListSelectionEvent(event, id, path) {
    var ids = id.split("-");
    let tableId = ids[0];
    let trId = ids[1];
    let selectionModel = document.getElementById(tableId + ".selection.model");
    selectionModel.value = "-1." + trId;
    let page = selectionModel.dataset.path;
    fireEvent(page, trId, "valueChanged-ListSelectionEvent");
}

/**
 * Tree node selection event.
 *
 * @param ida
 * @param path
 */
function valueChangedTreeSelectionEvent(event, id, path) {
    event.preventDefault();
    let ulName = document.getElementsByClassName("jtree")[0].getAttribute('name');
    let selectionInput = document.getElementsByName(ulName.split("-")[1])[0];
    selectionInput.value = id;
    fireEvent(path, id, "valueChanged-TreeSelectionEvent");
}

/**
 * Key pressed event. only for enter
 *
 * @param event
 * @param id
 * @param path
 */
function keyPressedKeyEvent(event, id, path) {
    if (event.keyCode === 13) {
        event.preventDefault();
        fireEvent(path, 13, "keyPressed-KeyEvent");
    }
}

/**
 * input type=text value change event.
 *
 * @param event
 * @param id
 * @param path
 */
function insertUpdateDocumentEvent(event, id, path) { // JTextField,JTextArea insertUpdate
    fireEvent(path, id, "insertUpdate-DocumentEvent");
}

/**
 * Send AJAX  request
 * @param path
 * @param id
 * @param javaEvent
 */
function fireEvent(path, id, javaEvent) {
    // store last focused element.
    localStorage.activeElement = document.activeElement.id;
    //send AJAX request.
    document.getElementById("action-remover").style.display = "block";
    sendAJAXRequest(receiveData, "path=" + path + "&method=" + javaEvent + "&id=" + id);
}

/**
 * Tab select event
 *
 * @param event
 * @param modelName
 * @param tabName
 * @param index
 */
function showTab(event, tabID, modelName, index) {
    event.preventDefault();
    // Declare all variables
    var i, tabcontent, tablinks;

    let tab = document.getElementById(tabID);

    // Get all elements with class="tab-content" and hide them
    tabcontent = tab.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = tab.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    //let selectedTab = document.getElementById(tabName).style.display = "block";
    tab.getElementsByClassName("tab-content")[index].style.display = "block";
    event.currentTarget.className += " active";

    document.getElementById(modelName).value = index;
}

/**
 * Toggles TD's editor visibility
 *
 * @param td
 */
function jtableCellEdit(event, td) {
    event.preventDefault();
    let col = td.dataset.col;
    let row = td.dataset.row;
    var tableId = td.closest("table").getAttribute("id");
    let selectionModel = document.getElementById(tableId + ".selection.model");
    selectionModel.value = col + "." + row;
    fireEvent(selectionModel.dataset.path, row, "valueChanged-ListSelectionEvent");
}

/**
 * Closes TD editor
 *
 * @param closeBtn
 */
function tdEditorClose(event, closeBtn) {
    event.preventDefault();
    let td_editor = closeBtn.parentElement;

    var tableId = closeBtn.closest("table").getAttribute("id");
    let selectionModel = document.getElementById(tableId + ".selection.model");
    selectionModel.value = -1 + "." + -1;
    fireEvent(selectionModel.dataset.path, -1, "valueChanged-ListSelectionEvent");
}

function closePopupMenu(popUpModel) {
    let parent = popUpModel.parentNode;
    parent.removeChild(popUpModel.nextSibling);
    parent.removeChild(popUpModel);
}