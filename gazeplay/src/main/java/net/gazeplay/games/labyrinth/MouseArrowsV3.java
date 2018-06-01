package net.gazeplay.games.labyrinth;

import javafx.geometry.Dimension2D;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import net.gazeplay.GameContext;
import net.gazeplay.commons.gaze.devicemanager.GazeEvent;
import net.gazeplay.commons.utils.stats.Stats;

public class MouseArrowsV3 extends MouseArrows {

    public MouseArrowsV3(double positionX, double positionY, double width, double height, GameContext gameContext,
            Stats stats, Labyrinth gameInstance) {
        super(positionX, positionY, width, height, gameContext, stats, gameInstance);

    }

    @Override
    protected void recomputeArrowsPositions() {
        return;
    }

    @Override
    protected void placementFleche() {

        Dimension2D dimension2D = gameContext.getGamePanelDimensionProvider().getDimension2D();

        double delta = dimension2D.getHeight() / 15;

        double x = gameInstance.entiereRecX + gameInstance.entiereRecWidth / 2 - delta;
        double y = gameInstance.entiereRecY - 2 * delta;

        this.buttonUp = new Rectangle(x, y + 0.4 * delta, buttonDimWidth, buttonDimHeight);
        this.buttonUp.setFill(new ImagePattern(new Image("data/labyrinth/images/upArrow.png"), 5, 5, 1, 1, true));
        this.indicatorUp = createProgressIndicator(x, y + 0.4 * delta, buttonDimWidth, buttonDimHeight);

        y = gameInstance.entiereRecY + gameInstance.entiereRecHeight;
        this.buttonDown = new Rectangle(x, y, buttonDimWidth, buttonDimHeight);
        this.buttonDown.setFill(new ImagePattern(new Image("data/labyrinth/images/downArrow.png"), 5, 5, 1, 1, true));
        this.indicatorDown = createProgressIndicator(x, y, buttonDimWidth, buttonDimHeight);

        y = gameInstance.entiereRecY + gameInstance.entiereRecHeight / 2 - delta;
        x = gameInstance.entiereRecX - 2.5 * delta;
        this.buttonLeft = new Rectangle(x, y, buttonDimHeight, buttonDimWidth);
        this.buttonLeft.setFill(new ImagePattern(new Image("data/labyrinth/images/leftArrow.png"), 5, 5, 1, 1, true));
        this.indicatorLeft = createProgressIndicator(x, y, buttonDimWidth, buttonDimHeight);

        x = gameInstance.entiereRecX + gameInstance.entiereRecWidth + 0.5 * delta;
        this.buttonRight = new Rectangle(x, y, buttonDimHeight, buttonDimWidth);
        this.buttonRight.setFill(new ImagePattern(new Image("data/labyrinth/images/rightArrow.png"), 5, 5, 1, 1, true));
        this.indicatorRight = createProgressIndicator(x, y, buttonDimWidth, buttonDimHeight);

    }

}
