//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package skyStriker.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import skyStriker.SkyStrikerMod;
import skyStriker.powers.SkyStriker.ShizukuPower;

import java.util.Iterator;

import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SSSpellCard;

public class ShizukuStance extends AbstractStance {
    public static final String STANCE_ID = SkyStrikerMod.makeID(ShizukuStance.class.getSimpleName());;
    private static final StanceStrings stanceString;
    private static long sfxId;

    public ShizukuStance() {
        this.ID = STANCE_ID;
        this.name = stanceString.NAME;
        this.updateDescription();
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
    }
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        int i = 0;
        Iterator var2 = AbstractDungeon.player.discardPile.group.iterator();
        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (c.hasTag(SkyStriker) && c.hasTag(SSSpellCard)) {
                i++;
            }
        }
        return type == DamageInfo.DamageType.NORMAL ? damage - i : damage;
    }
    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, ShizukuPower.POWER_ID));
        this.stopIdleSfx();
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
