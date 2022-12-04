//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package skyStriker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;
public class MultiRoleAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private int newCost;
    private boolean setCost;

    AbstractCard.CardTags tags;

    public MultiRoleAction(int numberOfCards, boolean optional, AbstractCard.CardTags cardTags) {
        this.newCost = 0;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = optional;
        this.setCost = false;
        this.tags=cardTags;

    }



    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {
                Iterator var5 = this.player.discardPile.group.iterator();
                CardGroup tmp =new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                AbstractCard c;
                while(var5.hasNext()) {
                    c = (AbstractCard)var5.next();
                    if(c.hasTag(tags))
                        tmp.addToRandomSpot(c);
                }
                if (tmp.size() == 0) this.isDone = true;
//                if (tmp.size() <= this.numberOfCards && !this.optional) {
//                    ArrayList<AbstractCard> cardsToMove = new ArrayList();
//                    var5 = cardsToMove.iterator();
//                    while(var5.hasNext()) {
//                        c = (AbstractCard)var5.next();
//                        if (this.player.hand.size() < 10) {
//                            this.player.hand.addToHand(c);
//                            c.retain=true;
//                            if (this.setCost) {
//                                c.setCostForTurn(this.newCost);
//                            }
//                            this.player.discardPile.removeCard(c);
//                        }
//
//                        c.lighten(false);
//                        c.applyPowers();
//                    }
//
//                    this.isDone = true;
//                } else {
                if (this.numberOfCards == 1) {
                    if (this.optional) {
                        AbstractDungeon.gridSelectScreen.open(tmp, this.numberOfCards, true, TEXT[0]);
                    } else {
                        AbstractDungeon.gridSelectScreen.open(tmp, this.numberOfCards, TEXT[0], false);
                    }
                } else if (this.optional) {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
                }
                    this.tickDuration();
//                }
            } else {
                this.isDone = true;
            }
        } else {
            Iterator var1;
            AbstractCard c;
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        c.retain=true;
                        c.exhaust=true;
                        if (this.setCost) {
                            c.setCostForTurn(this.newCost);
                        }
                        this.player.discardPile.removeCard(c);
                    }
                    else{
                        this.player.createHandIsFullDialog();
                    }

                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }

                for(var1 = this.player.discardPile.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {
                    c = (AbstractCard)var1.next();
                    c.unhover();
                    c.target_x = (float)CardGroup.DISCARD_PILE_X;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
            if (this.isDone) {
                var1 = this.player.hand.group.iterator();
                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    c.applyPowers();
                }
            }
        }
    }


    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
