package mandolin.component;

import java.io.File;
import java.io.FileFilter;

/**
 * Title: Mandolin
 * Description:
 * Copyright: B3
 * Company: B3
 *
 * @author Niranja Arjuna Jayasinghe
 * @version 1.0
 */
public class JFileChooser {

	public JFileChooser(String text){

	}

	public static final int APPROVE_OPTION = 0;

	public void setFileFilter(FileFilter filter){}

	public int showOpenDialog(){
		return 0;
	}

	public File getSelectedFile(){
		return null;
	}
}
