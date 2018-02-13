package net.gazeplay;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import javafx.scene.Scene;
import lombok.extern.slf4j.Slf4j;
import net.gazeplay.commons.utils.stats.Stats;
import net.gazeplay.games.biboules.Biboule;
import net.gazeplay.games.biboules.BibouleGamesStats;
import net.gazeplay.games.blocs.Blocs;
import net.gazeplay.games.blocs.BlocsGamesStats;
import net.gazeplay.games.bubbles.Bubble;
import net.gazeplay.games.bubbles.BubbleType;
import net.gazeplay.games.bubbles.BubblesGamesStats;
import net.gazeplay.games.creampie.CreamPie;
import net.gazeplay.games.creampie.CreampieStats;
import net.gazeplay.games.magiccards.MagicCards;
import net.gazeplay.games.magiccards.MagicCardsGamesStats;
import net.gazeplay.games.ninja.Ninja;
import net.gazeplay.games.ninja.NinjaStats;
import net.gazeplay.games.scratchcard.ScratchcardGamesStats;
import net.gazeplay.games.whereisit.WhereIsIt;
import net.gazeplay.games.whereisit.WhereIsItStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class DefaultGamesLocator implements GamesLocator {

    public static final String DEFAULT_AIMING_GAME_THUMBNAIL = "data/common/images/target.png";

    public static final String DEFAULT_SEARCHING_GAME_THUMBNAIL = "data/common/images/searching-magnifying-glass.png";

    @Override
    public List<GameSpec> listGames() {

        List<GameSpec> result = new ArrayList<>();

        result.add(
                new GameSpec(new GameSummary("Creampie", DEFAULT_AIMING_GAME_THUMBNAIL), new GameSpec.GameLauncher() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new CreampieStats(scene);
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext, GameSpec.GameVariant gameVariant,
                            Stats stats) {
                        return new CreamPie(gameContext, stats);
                    }
                }));

        result.add(new GameSpec(new GameSummary("Ninja", DEFAULT_AIMING_GAME_THUMBNAIL), new GameSpec.GameLauncher() {
            @Override
            public Stats createNewStats(Scene scene) {
                return new NinjaStats(scene);
            }

            @Override
            public GameLifeCycle createNewGame(GameContext gameContext, GameSpec.GameVariant gameVariant, Stats stats) {
                return new Ninja(gameContext, stats);
            }
        }));

        result.add(new GameSpec(new GameSummary("MagicCards", DEFAULT_SEARCHING_GAME_THUMBNAIL),
                new GameSpec.GameVariantGenerator() {
                    @Override
                    public Set<GameSpec.GameVariant> getVariants() {
                        return Sets.newLinkedHashSet(Lists.newArrayList(

                                new GameSpec.DimensionGameVariant(2, 2),

                                new GameSpec.DimensionGameVariant(2, 3),

                                new GameSpec.DimensionGameVariant(3, 2),

                                new GameSpec.DimensionGameVariant(3, 3)

                ));
                    }
                }, new GameSpec.GameLauncher<Stats, GameSpec.DimensionGameVariant>() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new MagicCardsGamesStats(scene);
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext,
                            GameSpec.DimensionGameVariant gameVariant, Stats stats) {
                        return new MagicCards(gameContext, gameVariant.getWidth(), gameVariant.getHeight(), stats);
                    }
                }));

        result.add(new GameSpec(new GameSummary("Blocks", DEFAULT_SEARCHING_GAME_THUMBNAIL),
                new GameSpec.GameVariantGenerator() {
                    @Override
                    public Set<GameSpec.GameVariant> getVariants() {
                        return Sets.newLinkedHashSet(Lists.newArrayList(

                                new GameSpec.DimensionGameVariant(2, 2),

                                new GameSpec.DimensionGameVariant(2, 3),

                                new GameSpec.DimensionGameVariant(3, 3)

                ));
                    }
                }, new GameSpec.GameLauncher<Stats, GameSpec.DimensionGameVariant>() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new BlocsGamesStats(scene);
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext,
                            GameSpec.DimensionGameVariant gameVariant, Stats stats) {
                        return new Blocs(gameContext, gameVariant.getWidth(), gameVariant.getHeight(), true, 1, false,
                                stats);
                    }
                }));

        result.add(new GameSpec(new GameSummary("ScratchCard", DEFAULT_SEARCHING_GAME_THUMBNAIL),
                new GameSpec.GameLauncher() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new ScratchcardGamesStats(scene);
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext, GameSpec.GameVariant gameVariant,
                            Stats stats) {
                        return new Blocs(gameContext, 100, 100, false, 0.6f, true, stats);
                    }
                }));

        result.add(new GameSpec(new GameSummary("ColoredBubbles", DEFAULT_AIMING_GAME_THUMBNAIL),
                new GameSpec.GameLauncher() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new BubblesGamesStats(scene);
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext, GameSpec.GameVariant gameVariant,
                            Stats stats) {
                        return new Bubble(gameContext, BubbleType.COLOR, stats, true);
                    }
                }));

        result.add(new GameSpec(new GameSummary("PortraitBubbles", DEFAULT_AIMING_GAME_THUMBNAIL),
                new GameSpec.GameLauncher() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new BubblesGamesStats(scene);
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext, GameSpec.GameVariant gameVariant,
                            Stats stats) {
                        return new Bubble(gameContext, BubbleType.PORTRAIT, stats, false);
                    }
                }));

        result.add(new GameSpec(new GameSummary("WhereIsTheAnimal", DEFAULT_AIMING_GAME_THUMBNAIL),
                new GameSpec.GameVariantGenerator() {
                    @Override
                    public Set<GameSpec.GameVariant> getVariants() {
                        return Sets.newLinkedHashSet(Lists.newArrayList(

                                new GameSpec.DimensionGameVariant(2, 2),

                                new GameSpec.DimensionGameVariant(2, 3),

                                new GameSpec.DimensionGameVariant(3, 2),

                                new GameSpec.DimensionGameVariant(3, 3)

                ));
                    }
                }, new GameSpec.GameLauncher<Stats, GameSpec.DimensionGameVariant>() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new WhereIsItStats(scene, WhereIsIt.WhereIsItGameType.ANIMALNAME.getGameName());
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext,
                            GameSpec.DimensionGameVariant gameVariant, Stats stats) {
                        return new WhereIsIt(WhereIsIt.WhereIsItGameType.ANIMALNAME, gameVariant.getWidth(),
                                gameVariant.getHeight(), false, gameContext, stats);
                    }

                }));

        result.add(new GameSpec(new GameSummary("WhereIsTheColor", DEFAULT_AIMING_GAME_THUMBNAIL),
                new GameSpec.GameVariantGenerator() {
                    @Override
                    public Set<GameSpec.GameVariant> getVariants() {
                        return Sets.newLinkedHashSet(Lists.newArrayList(

                                new GameSpec.DimensionGameVariant(2, 2),

                                new GameSpec.DimensionGameVariant(2, 3),

                                new GameSpec.DimensionGameVariant(3, 2),

                                new GameSpec.DimensionGameVariant(3, 3)

                ));
                    }
                }, new GameSpec.GameLauncher<Stats, GameSpec.DimensionGameVariant>() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new WhereIsItStats(scene, WhereIsIt.WhereIsItGameType.COLORNAME.getGameName());
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext,
                            GameSpec.DimensionGameVariant gameVariant, Stats stats) {
                        return new WhereIsIt(WhereIsIt.WhereIsItGameType.COLORNAME, gameVariant.getWidth(),
                                gameVariant.getHeight(), false, gameContext, stats);
                    }
                }));

        result.add(new GameSpec(new GameSummary("WhereIsIt", DEFAULT_AIMING_GAME_THUMBNAIL),
                new GameSpec.GameVariantGenerator() {
                    @Override
                    public Set<GameSpec.GameVariant> getVariants() {
                        return Sets.newLinkedHashSet(Lists.newArrayList(

                                new GameSpec.DimensionGameVariant(2, 2),

                                new GameSpec.DimensionGameVariant(2, 3),

                                new GameSpec.DimensionGameVariant(3, 2),

                                new GameSpec.DimensionGameVariant(3, 3)

                ));
                    }
                }, new GameSpec.GameLauncher<Stats, GameSpec.DimensionGameVariant>() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new WhereIsItStats(scene, WhereIsIt.WhereIsItGameType.CUSTOMIZED.getGameName());
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext,
                            GameSpec.DimensionGameVariant gameVariant, Stats stats) {
                        return new WhereIsIt(WhereIsIt.WhereIsItGameType.CUSTOMIZED, gameVariant.getWidth(),
                                gameVariant.getHeight(), false, gameContext, stats);
                    }
                }));

        result.add(
                new GameSpec(new GameSummary("Biboules", DEFAULT_AIMING_GAME_THUMBNAIL), new GameSpec.GameLauncher() {
                    @Override
                    public Stats createNewStats(Scene scene) {
                        return new BibouleGamesStats(scene);
                    }

                    @Override
                    public GameLifeCycle createNewGame(GameContext gameContext, GameSpec.GameVariant gameVariant,
                            Stats stats) {
                        return new Biboule(gameContext, stats);
                    }
                }));

        log.info("Games found : {}", result.size());

        return result;
    }

}
