package skyStriker.powers.SkyStriker;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import skyStriker.SkyStrikerMod;
import skyStriker.util.TextureLoader;

import static skyStriker.SkyStrikerMod.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class JammingWavesPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = SkyStrikerMod.makeID("JammingWavesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("JammingWaves84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("JammingWaves32.png"));

    public JammingWavesPower( AbstractCreature owner, int times) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = times;
        type = PowerType.BUFF;
//        this.priority = 75;
        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

//    public void playApplyPowerSfx() {
//        CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
//    }
public int onAttacked(DamageInfo info, int damageAmount) {
    if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
        this.flash();
        this.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SHIELD));
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    return damageAmount;
}

    public void atStartOfTurn() {
        this.addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this.ID));
    }

    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new JammingWavesPower(owner, amount);
    }
}
