package skyStriker.relics.SkyStriker;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import skyStriker.DefaultMod;
import skyStriker.actions.SkyStrikerDowngradeAction;
import skyStriker.actions.SkyStrikerUpgradeAction;
import skyStriker.powers.SkyStriker.JammingWavesPower;
import skyStriker.powers.SkyStriker.SkyStrikerUpgradePower;
import skyStriker.util.TextureLoader;

import java.util.Iterator;

import static skyStriker.DefaultMod.makeRelicOutlinePath;
import static skyStriker.DefaultMod.makeRelicPath;
import static skyStriker.cards.SkyStrikerCardTags.SkyStriker;
import static skyStriker.cards.SkyStrikerCardTags.SpellCard;

public class SkyStrikerDefaultRelic extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 1 energy.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("SkyStrikerDefaultRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SkyStriker_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("SkyStriker_relic.png"));
    int  upgraded=0;

    public SkyStrikerDefaultRelic() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    public void atTurnStart() {
        this.flash();
        this.counter = 0;
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        if(this.counter==0)
//            this.addToBot(new SkyStrikerDowngradeAction());
    }

    // Flash at the start of Battle.
//    @Override
//    public void atBattleStartPreDraw() {
//        flash();
//    }


//    public void onUseCard(AbstractCard card, UseCardAction action) {
        public void onRefreshHand() {
        AbstractPlayer p = AbstractDungeon.player;
//        if (card.hasTag(SpellCard)&& !card.exhaust) {
//            ++this.counter;
//            if (this.counter % 3 == 0) {
//                this.counter = 0;
//                this.flash();
//                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//                this.addToBot(new SkyStrikerUpgradeAction());
//            }
//        }
        int i=0;
        Iterator var2 = p.discardPile.group.iterator();
        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (c.hasTag(SkyStriker) && c.hasTag(SpellCard)) {
                i++;
            }
        }
        this.counter=i;
        if (this.counter >= 3 ) {
            upgraded=1;
//                this.flash();
//                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new SkyStrikerUpgradeAction());
        }
        if (this.counter==0 && upgraded==1)
        {
            this.addToBot(new SkyStrikerDowngradeAction());
            upgraded=0;
        }


//        if (this.counter<3)
//        {
//            this.addToBot(new SkyStrikerDowngradeAction());
//        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SkyStrikerDefaultRelic();
    }

    public void onVictory() {
        this.counter = -1;
    }
    // Gain 1 energy on equip.
//    @Override
//    public void onEquip() {
//        AbstractDungeon.player.energy.energyMaster += 1;
//    }
//
//    // Lose 1 energy on unequip.
//    @Override
//    public void onUnequip() {
//        AbstractDungeon.player.energy.energyMaster -= 1;
//    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
