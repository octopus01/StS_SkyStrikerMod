package skyStriker.powers.SkyStriker;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skyStriker.SkyStrikerMod;
import skyStriker.util.TextureLoader;

import java.util.Iterator;

import static skyStriker.SkyStrikerMod.makePowerPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SSSpellCard;

//Gain 1 dex for the turn for each card played.

public class SkyStrikerUpgradePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = SkyStrikerMod.makeID("SkyStrikerUpgradePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public SkyStrikerUpgradePower( AbstractCreature owner, int turns) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = turns;
        type = PowerType.BUFF;
        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
    }
    public void duringTurn() {
                AbstractPlayer p = AbstractDungeon.player;
                this.upgradeSkyStrikerSpellCards(p.hand);
                this.upgradeSkyStrikerSpellCards(p.drawPile);
                this.upgradeSkyStrikerSpellCards(p.discardPile);
                this.upgradeSkyStrikerSpellCards(p.exhaustPile);
            }



        private void upgradeSkyStrikerSpellCards(CardGroup cardGroup) {
            Iterator var2 = cardGroup.group.iterator();
            while (var2.hasNext()) {
                AbstractCard c = (AbstractCard) var2.next();
                if (c.canUpgrade() && c.hasTag(SkyStriker) && c.hasTag(SSSpellCard)) {
                    if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                        c.superFlash();
                    }
                    c.upgrade();

                }
            }
        }

                public void atEndOfRound () {
                    this.flash();
                    if (this.amount == 0) {
                        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
                    } else {
                        this.addToBot(new ReducePowerAction(this.owner, this.owner, this.POWER_ID, 1));
                    }

                }

                // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
                @Override
                public void updateDescription () {
                    this.description = DESCRIPTIONS[0];
                }

                @Override
                public AbstractPower makeCopy () {
                    return new SkyStrikerUpgradePower(owner, amount);
                }
            }