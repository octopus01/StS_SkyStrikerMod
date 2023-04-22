package skyStriker.powers.SkyStriker;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skyStriker.SkyStrikerMod;
import skyStriker.util.TextureLoader;

import static skyStriker.SkyStrikerMod.makePowerPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;


public class KainaPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = SkyStrikerMod.makeID("KainaPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Earth84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Earth32.png"));

    public KainaPower( AbstractCreature owner,  final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.amount=amount;
        this.owner = owner;
        isTurnBased=false;
        type = PowerType.BUFF;
        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.hasTag(SkyStriker))  {
            this.addToBot(new HealAction(AbstractDungeon.player ,AbstractDungeon.player, 1));
        }
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new KainaPower(owner, amount);
    }
}
