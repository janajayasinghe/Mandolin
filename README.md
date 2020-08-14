Main idea of mandolin framework is to convert swing applications into web applications without touching business logic. Understanding and re-cording legacy applications is time consuming and costly. but unfortunately, we all must do it since swing is already outdated. Not only outdated, some of related technologies like web start will be removed from java-core package soon. Therefore, I decided to develop a framework that reduces time and cost. 

## Warning
This is the first version of the framework and it will not be able to convert whole application into a web application yet. instead you can convert JPanel by JPanel into webpages and insert them into a html div tag using AJAX call. 

## How to use it. 
There are only five things that you want to think of. 
1.	Remove swing imports and import necessary classes from this framework. 
2.	You need to have your own servlet class and handle other things that do not provide by the framework. For example, session validation. Withing your servlet, you need to call MandolinServlet.play(req,frame) in order to generate HTML page. Frame object should be a sub class of JFrame. Implementing JFrame.createContainerByName() method is mandatory. Please see the example code. 
```
WebFrame frame = new WebFrame(this, dbConnection, httpSession);
String htmlPage = MandolinServlet.play(req,frame);
```
3.	Comment out irrelevant swing method calls to this framework. 
4.	Copy html/script/mandolin.js to Contextpath/web/script/mandolin.js
5.	Copy html/css/mandolin.css to Contextpath/web/css/mandolin.css


## Important packages

1.	**HTMLContainer** – Jframe, JPanel JDialog are some of examples for these types of classes. HTMLContainers have components map that holds its child elements. These children are rendered according to the layout provide by the user. A HTMLContainer has fireAction method which is called when a user performs an action. (eg: button click.) HTMLContainer.setData method binds data that comes from the front-end to HTMLComponets. Thismethod is called from MandolinServlet.paly method.
2.	**HTMLComponent** – Example HTMLComponenets are JTextField, JButton and JComboBox. All the added actions are stored in the actions list. At the page rendering time, these actions are added to the html tag as HTML events. Whenever a user adds a HTMLComponent to a HTMLContainer, a random id is assigned to the component if the id is null. Same id is used as the component’s name If the name field is empty. (having a name is mandatory for a HTML element to get its value at the backend.) HTMLComponent.setData method is called from HTMLContainer when an action is fired by the users.  This method should be overridden by the classe that extends HTMLComponent. 
3.	**Layout** – I have ignored swing layouts intentionally and added only two layouts to the framework which are FlowLayout and GridLayout. Both of these layouts use CSS display:table to arrange html tags. In addition to these layouts, Mandolin has EmptyLayout which is used as the default layout for HTMLContainers since having a layout is mandatory to render children components. 
4.	**Border** – currently this feature is supported only by HTMLContainers. Border is assigned to the wrapper div/ fieldset of each HTMLConatainer. Fieldset is for title borders.
5.	**Event** – Event objects are created by HTMLContainer.fireAction method and it has HTMLComponent ID. 
6.	**Listener** – Listeners have String constant that specify the javascript function.it has 3 default parameters which are event, this.dataset.id and this.dataset.path. therefore, every html tag should have data-id and data-path to fire an action.

## Mechanism of page rendering.

HTMLContainers and HTMLComponents have toHTML method that generate HTML. HTMLComponent directly returns the html tag. For example, JButton.toHTML method returns <input type=’button’> tag. But HTMLContainers use layouts to generate html tags in different arrangement according to the users need. See the layout.visit method. This method calls itself recursively from top HTMLContainer to HtMLComponet within the component tree. Path variable is constructed within this method and assigned to components. 

## Mechanism of user action. 

Every HTMLComponet should have a path variable to identify the parent HTMLContainer that the HTMLComponent belong to. For example, in a JDialog, there may be dozens of JPanels. In a one JPanel there is a JButton. When a user clicks on that JButton, framework should be able to identify the JDialog specifically in order to fire the action. To perform button actions, JDialog implement one of listener class. So the generateID method checks whether the HTMLconatiner is a sub type of a Listener and then adds ‘’$” as a prefix to the ID.The path is constructed from component IDs, if a component does not have an id, index of that component in the HTMLContainer is used as the id. Then anytime any component can be taken from anywhere without traversing through whole component tree.  

Above path and the HTMLContainer’s id are passed to the Javascript function as parameters and when a user performs an action, those variables will be sent to the backend. Id is used to identify the HTMLComponent (eg: JButton, JTextField) and the path is used to identify the HTMLContainer (eg: Jpanel) that implements listener classes. 
If the bellow path is received to the back, $1(last id with $ prefix) will be taken as the parent HTMLContainer and “bjhslajj” will be taken as the id of HTMLComponent. 

`Eg: $EmployeePanel-1-3-cfdvklsk-$1-3-bjhslajj`

User actions are fired from JFrame.runAction method in a separate thread. Thread is used to support JDialogs since JDialogs are generated inside methods (eg: actionPerformed) at runtime. Most of the time, these methods continue according to the user inputs of the JDialog. It means, the framework has to pause the method execution and wait until user performs actions on the JDialog. If the method does not call JDialog, method execution continues until the end.See the JDialog.setVisible and JDialog.dispose methods.

