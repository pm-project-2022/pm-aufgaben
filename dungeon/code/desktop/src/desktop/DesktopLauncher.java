package desktop;

import Gui.Gui;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.DungeonApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import controller.LibgdxSetup;
import controller.MainController;
import tools.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class DesktopLauncher {
    public static void run(MainController mc) {


        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.WINDOW_WIDTH;
        config.height = Constants.WINDOW_HEIGHT;
        config.foregroundFPS = Constants.FRAME_RATE;
        config.title = Constants.WINDOW_TITLE;
        config.addIcon(Constants.LOGO_PATH, Files.FileType.Internal);
        new DungeonApplication(new LibgdxSetup(mc), config, 0);
    }
}
