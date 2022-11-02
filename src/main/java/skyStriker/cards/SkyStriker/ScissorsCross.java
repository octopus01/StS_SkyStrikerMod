package skyStriker.cards.SkyStriker;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import skyStriker.DefaultMod;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;

import static skyStriker.DefaultMod.makeCardPath;
public class ScissorsCross extends CustomCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ScissorsCross.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("ScissorsCross.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG =3;
//    private static int i=1;

    public ScissorsCross() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

        this.tags.add(SkyStrikerCardTags.SkyStriker);
        this.tags.add(SkyStrikerCardTags.SpellCard);
        this.isMultiDamage = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (int i = 0; i < 2; ++i) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {


//        String nameplus;
//        if (i==1) nameplus=name+"+";
//        else nameplus=name;
        if (!upgraded) {
//            upgradeName();
//                i=0;

                baseDamage=11;
                this.upgraded=true;
//                this.name=nameplus;
                this.upgradedDamage = true;
                this.initializeTitle();
//            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
