package net.gazeplay.games.labyrinth;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import net.gazeplay.GameContext;
import net.gazeplay.commons.utils.stats.Stats;
import net.gazeplay.games.labyrinth.Labyrinth;

/*
 * MouseArrowsV1 : 
 * Arrows are at the left of the Labyrinth.
 * This version is not accessible for the menu
 * This version is not suitable for eye tracking. 
 */

public class MouseArrowsV1 extends MouseArrows {

    public MouseArrowsV1(double positionX, double positionY, double width, double height, GameContext gameContext,
            Stats stats, Labyrinth gameInstance) {

        super(positionX, positionY, width, height, gameContext, stats, gameInstance);
    }

    @Override
    protected void recomputeArrowsPositions() {
        return;
    }

    protected void placementFleche() {
        Dimension2D dimension2D = gameContext.getGamePanelDimensionProvider().getDimension2D();

        double delta = dimension2D.getWidth() * 0.01;

        double x = dimension2D.getWidth() * 0.05;
        double y = dimension2D.getHeight() * 0.4;
        this.buttonUp = new Rectangle(x + delta, y - delta, buttonDimWidth, buttonDimHeight);
        this.buttonUp.setFill(new ImagePattern(new Image("data/labyrinth/images/upArrow.png"), 5, 5, 1, 1, true));
        this.indicatorUp = createProgressIndicator(x + delta, y - delta, buttonDimWidth, buttonDimHeight);

        y = y + 1.5 * buttonDimHeight;
        this.buttonDown = new Rectangle(x + delta, y - 2.5 * delta, buttonDimWidth, buttonDimHeight);
        this.buttonDown.setFill(new ImagePattern(new Image("data/labyrinth/images/downArrow.png"), 5, 5, 1, 1, true));
        this.indicatorDown = createProgressIndicator(x + delta, y - 2.5 * delta, buttonDimWidth, buttonDimHeight);

        y = y - 1.1 * buttonDimHeight;
        x = x - 0.4 * buttonDimWidth;
        this.buttonLeft = new Rectangle(x, y, buttonDimHeight, buttonDimWidth);
        this.buttonLeft.setFill(new ImagePattern(new Image("data/labyrinth/images/leftArrow.png"), 5, 5, 1, 1, true));
        this.indicatorLeft = createProgressIndicator(x, y, buttonDimWidth, buttonDimHeight);

        x = x + 1.1 * buttonDimWidth;
        this.buttonRight = new Rectangle(x, y, buttonDimHeight, buttonDimWidth);
        this.buttonRight.setFill(new ImagePattern(new Image("data/labyrinth/images/rightArrow.png"), 5, 5, 1, 1, true));
        this.indicatorRight = createProgressIndicator(x, y, buttonDimWidth, buttonDimHeight);

    }

}
