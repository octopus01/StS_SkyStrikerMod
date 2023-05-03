package skyStriker.UI;

import basemod.BaseMod;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import skyStriker.cards.SkyStrikerCurrentScreen;
import skyStriker.characters.TheSkyStriker;

public class ExtraDeckTopPanel extends TopPanelItem {
    private static final Texture IMG = new Texture("skyStrikerResources/images/ui/EXdeck.png");
    public static final String ID = "SkyStriker:ExtraDeck";

    public ExtraDeckTopPanel() {
        super(IMG, ID);
    }

    @Override
    protected void onClick() {
        if (AbstractDungeon.player instanceof TheSkyStriker) {
            boolean clickedDeckButton = InputHelper.justClickedLeft;
            if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
                AbstractDungeon.closeCurrentScreen();
                BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
            } else if (!AbstractDungeon.isScreenUp) {
                BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
            } else if (AbstractDungeon.screen == SkyStrikerCurrentScreen.ExtraDeckScreen) {
                AbstractDungeon.screenSwap = false;
                if (AbstractDungeon.previousScreen == SkyStrikerCurrentScreen.ExtraDeckScreen) {
                    AbstractDungeon.previousScreen = null;
                }
                AbstractDungeon.closeCurrentScreen();
                CardCrawlGame.sound.play("DECK_CLOSE", 0.05F);
            } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.DEATH) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
                AbstractDungeon.deathScreen.hide();
                BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
            } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.BOSS_REWARD) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.BOSS_REWARD;
                AbstractDungeon.bossRelicScreen.hide();
                BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
            } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.SHOP) {
                AbstractDungeon.overlayMenu.cancelButton.hide();
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.SHOP;
                BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
            } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP && !AbstractDungeon.dungeonMapScreen.dismissable) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.MAP;
                BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
            } else if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.MAP) {
                if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.INPUT_SETTINGS && clickedDeckButton) {
                    if (AbstractDungeon.previousScreen != null) {
                        AbstractDungeon.screenSwap = true;
                    }

                    AbstractDungeon.closeCurrentScreen();
                    BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.CARD_REWARD;
                    AbstractDungeon.dynamicBanner.hide();
                    BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.GRID;
                    AbstractDungeon.gridSelectScreen.hide();
                    BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
                } else if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT) {
                    AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.HAND_SELECT;
                    BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
                }
            } else {
                if (AbstractDungeon.previousScreen != null) {
                    AbstractDungeon.screenSwap = true;
                }

                AbstractDungeon.closeCurrentScreen();
                BaseMod.openCustomScreen(SkyStrikerCurrentScreen.ExtraDeckScreen);
            }

            InputHelper.justClickedLeft = false;
        }
    }

    protected void onHover() {
        this.angle = MathHelper.angleLerpSnap(this.angle, 15.0F);
        this.tint.a = 0.25F;
    }

    protected void onUnhover() {
        this.angle = MathHelper.angleLerpSnap(this.angle, 0.0F);
        this.tint.a = 0.0F;
    }


}
