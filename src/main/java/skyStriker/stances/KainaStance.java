//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package skyStriker.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import skyStriker.SkyStrikerMod;
import skyStriker.powers.SkyStriker.KainaPower;

public class KainaStance extends AbstractStance {
    public static final String STANCE_ID = SkyStrikerMod.makeID(KainaStance.class.getSimpleName());
    private static final StanceStrings stanceString;
    private static long sfxId;

    public KainaStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
    }
    protected void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public void updateDescription() {
        this.description = stanceString.DESCRIPTION[0];
    }

    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.04F;
                AbstractDungeon.effectsQueue.add(new CalmParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Calm"));
        }

    }

    public void onEnterStance() {
        if (sfxId != -1L) {
            this.stopIdleSfx();
        }

        CardCrawlGame.sound.play("STANCE_ENTER_CALM");
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_CALM");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SKY, true));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                      new KainaPower(AbstractDungeon.player, 0), 0));
    }

    public void onExitStance() {
        this.stopIdleSfx();
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, KainaPower.POWER_ID));
    }

    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("STANCE_LOOP_CALM", sfxId);
            sfxId = -1L;
        }

    }


    static {
        stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
        sfxId = -1L;
    }
}
