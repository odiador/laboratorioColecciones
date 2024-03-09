package co.edu.uniquindio.estructuras.tienda.logicviewcontrollers;

import java.io.IOException;

import co.edu.uniquindio.estructuras.tienda.logiccontrollers.RAMController;
import co.edu.uniquindio.estructuras.tienda.services.ICloseableController;
import co.edu.uniquindio.estructuras.tienda.utils.FxmlPerspective;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuPrincipalLogicController {
	private Interpolator interpolator;

	private ParallelTransition transicionCargando;

	private BorderPane loadingLayer, mainLayer;

	private static MenuPrincipalLogicController instance;

	private SVGPath svgShoppingCard;

	private FxmlPerspective perspective;

	public static MenuPrincipalLogicController getInstance() {
		if (instance == null)
			instance = new MenuPrincipalLogicController();
		return instance;
	}

	public void cargarTransicionCargando(SVGPath svg1, SVGPath svg2) {
		transicionCargando = new ParallelTransition(createRotateAnim(svg1, 0, 360), createRotateAnim(svg2, 360, 0));
	}

	public void cargarMenuCargando(BorderPane loadingLayer) {
		this.loadingLayer = loadingLayer;
	}

	public Image cropImage(Image img, int radius) {
		double d = Math.min(img.getWidth(), img.getHeight());
		double x = (d - img.getWidth()) / 2;
		double y = (d - img.getHeight()) / 2;
		Canvas canvas = new Canvas(d, d);
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.fillRoundRect(0, 0, d, d, radius, radius);
		g.setGlobalBlendMode(BlendMode.SRC_ATOP);
		g.drawImage(img, x, y);
		return canvas.snapshot(null, null);
	}

	private RotateTransition createRotateAnim(SVGPath svg, double from, double to) {
		RotateTransition animacion = new RotateTransition(Duration.millis(800), svg);
		animacion.setFromAngle(from);
		animacion.setToAngle(to);
		animacion.setInterpolator(getInterpolator());
		animacion.setCycleCount(-1);
		return animacion;
	}

	private Interpolator getInterpolator() {
		if (interpolator == null)
			interpolator = new Interpolator() {

				@Override
				protected double curve(double t) {
					return 2 * t * t * (3 - 2 * t);
				}
			};
		return interpolator;
	}

	public void ejecutarProceso(Runnable runnable) {
		new Thread(() -> {
//			showPane(loadingLayer);
//			transicionCargando.playFromStart();
			runnable.run();
//			hidePane(loadingLayer);
//			transicionCargando.stop();
		}).start();
	}

	private void showPane(BorderPane pane) {
		FadeTransition anim = new FadeTransition(Duration.millis(200), pane);
		pane.setDisable(false);
		anim.setFromValue(0);
		anim.setToValue(1);
		anim.play();

	}

	private void hidePane(BorderPane pane) {
		FadeTransition anim = new FadeTransition(Duration.millis(200), pane);
		anim.setFromValue(1);
		anim.setToValue(0);
		anim.setOnFinished(e -> pane.setDisable(true));
		anim.play();
	}

	public void cargarMenuCentral(BorderPane mainLayer) {
		this.mainLayer = mainLayer;
	}

	public void mostrarCarrito() {
		MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
			Platform.runLater(() -> {
				mainLayer.setRight(mainLayer.getRight() == null ? perspective.getPerspective() : null);
			});
		});
	}

	private void cerrarCarrito() {
		mainLayer.setRight(null);
	}

	public void irAClientes() {
		MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
			try {
				cambiarPerspectiva(FxmlPerspective.loadPerspective("gestionClientes"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	public void cambiarPerspectiva(FxmlPerspective perspective) {
		Platform.runLater(() -> mainLayer.setCenter(perspective.getPerspective()));
	}

	public void cambiarPerspectiva(Parent parent) {
		Platform.runLater(() -> mainLayer.setCenter(parent));
	}

	public void irAProductos() {
		MenuPrincipalLogicController.getInstance().ejecutarProceso(() -> {
			try {
				cambiarPerspectiva(FxmlPerspective.loadPerspective("inventario"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void inicializarListeners(SVGPath svgShoppingCard) {
		RAMController.getInstance().addCarritoListener(carrito -> {
			if (carrito != null)
				svgShoppingCard.setFill(Color.BLACK);
			else
				svgShoppingCard.setFill(Color.TRANSPARENT);
		});

	}

	public void inicializarPerspectivas() {
		try {
			perspective = FxmlPerspective.loadPerspective("carritoCompras");
			ICloseableController controller = (ICloseableController) perspective.getController();
			controller.setCloseMethod(this::cerrarCarrito);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
