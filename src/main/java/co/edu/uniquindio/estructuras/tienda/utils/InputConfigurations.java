package co.edu.uniquindio.estructuras.tienda.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class InputConfigurations {

	public static ChangeListener<String> setAsIntegerTextfield(TextField tf, int value) {
		ChangeListener<String> listener = new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty())
					return;
				try {
					int num = Integer.parseInt(tf.getText());
					if (num > value) {
						observable.removeListener(this);
						tf.setText(value + "");
						observable.addListener(this);
					} else if (num < 0) {
						throw new Exception();
					} else {
						observable.removeListener(this);
						tf.setText(num + "");
						observable.addListener(this);
					}
				} catch (Exception e) {
					observable.removeListener(this);
					tf.setText(oldValue);
					observable.addListener(this);
				}
			}
		};
		tf.textProperty().addListener(listener);
		return listener;
	}

}
