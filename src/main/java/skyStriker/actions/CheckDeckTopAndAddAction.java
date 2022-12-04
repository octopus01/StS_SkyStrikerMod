//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package skyStriker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import skyStriker.cards.SkyStrikerCardTags;

import java.util.Iterator;

public class CheckDeckTopAndAddAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private float startingDuration;
    AbstractCard.CardTags tags;

    int addNum;

    public CheckDeckTopAndAddAction(int numCards, int addNum , AbstractCard.CardTags tags) {
        this.amount = numCards;
        if (AbstractDungeon.player.hasRelic("GoldenEye")) {
            AbstractDungeon.player.getRelic("GoldenEye").flash();
            this.amount += 2;
        }

        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.tags=tags;
        this.addNum=addNum;
    }

    public void update() {
        boolean canClear=true;
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.isDone = true;
        } else {
            Iterator var1;
            AbstractCard c;
            if (this.duration == this.startingDuration) {


                if (AbstractDungeon.player.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                CardGroup tmpGroup = new CardGroup(CardGroupType.UNSPECIFIED);
                if (this.amount != -1) {
                    for(int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); ++i) {
                        tmpGroup.addToTop((AbstractCard)AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
                    }
                } else {
                    Iterator var5 = AbstractDungeon.player.drawPile.group.iterator();
                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        tmpGroup.addToBottom(c);
                    }
                }
                AbstractDungeon.gridSelectScreen.open(tmpGroup, this.addNum, true, TEXT[0]);
               } else if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (AbstractDungeon.player.hand.size() == 10) {
                        AbstractDungeon.player.createHandIsFullDialog();
                    }
                    else {
                        if(c.hasTag(SkyStrikerCardTags.SkyStriker)) {
                            AbstractDungeon.player.drawPile.removeCard(c);
                            AbstractDungeon.player.hand.addToTop(c);
                        }
                        else{

                            canClear=false; 
                        }
                    }
                }
                if(canClear) {
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                    AbstractDungeon.gridSelectScreen.selectedCards.clear();
                }
                else{
                    AbstractDungeon.gridSelectScreen.selectedCards.clear();
                    AbstractDungeon.effectList.add(new ThoughtBubble(
                            AbstractDungeon.player.dialogX,  AbstractDungeon.player.dialogY, 3.0F, "选择的卡片不是闪刀", true));
                }
            }
            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("BetterToHandAction");
        TEXT = uiStrings.TEXT;
    }
}
