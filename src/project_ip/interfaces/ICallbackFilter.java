package project_ip.interfaces;

import java.awt.image.BufferedImage;

public interface ICallbackFilter {
    int apply(int x, int y, BufferedImage image);
}
