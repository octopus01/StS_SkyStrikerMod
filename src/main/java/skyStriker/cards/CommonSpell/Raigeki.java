package skyStriker.cards.CommonSpell;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import skyStriker.DefaultMod;
import skyStriker.cards.AbstractDynamicCard;
import skyStriker.cards.SkyStrikerCardTags;
import skyStriker.characters.TheSkyStriker;
import skyStriker.stances.HayateStance;

import java.util.Objects;

import static skyStriker.DefaultMod.makeCardPath;
@AutoAdd.Ignore
public class Raigeki extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Raigeki.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("Raigeki.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheSkyStriker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 4;


    public Raigeki() {
        super(ID,  IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

        this.tags.add(SkyStrikerCardTags.SkyStriker);
        this.tags.add(SkyStrikerCardTags.SpellCard);
        this.isMultiDamage = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(Objects.equals(p.stance.ID, HayateStance.STANCE_ID)) damageTypeForTurn= DamageInfo.DamageType.HP_LOSS;
        for(int i=0;i<2;i++) {
            this.addToBot(new SFXAction("ATTACK_HEAVY"));
            this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
            this.addToBot(new WaitAction(0.1F));
        }

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgraded = true;
            baseDamage=12;
            this.upgradedDamage = true;
//            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
    public void downgrade(){
        if(upgraded){
            upgraded=false;
//            baseDamage=8;
            this.initializeTitle();
            initializeDescription();
        }
    }
}
