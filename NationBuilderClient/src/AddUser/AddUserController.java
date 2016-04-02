package AddUser;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by crims_000 on 4/2/2016.
 */
public class AddUserController {
    public RadioButton adminSelect;
    public RadioButton studentSelect;
    public TextField userEntry;
    public TextField passEntry;
    public Button submitButton;

    public void loadInterface(){
        ToggleGroup group = new ToggleGroup();
        adminSelect.setToggleGroup(group);

        studentSelect.setToggleGroup(group);
        studentSelect.setSelected(true);
    }

    public void submitRequest(ActionEvent actionEvent) {
    }
}
