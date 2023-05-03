package skyStriker.cards;

import basemod.abstracts.CustomSavable;
import basemod.abstracts.CustomSavableRaw;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skyStriker.SkyStrikerMod;
import skyStriker.characters.TheSkyStriker;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SkyStrikerCardGroup implements CustomSavable<List<String>> {

    public static final Logger logger = LogManager.getLogger(SkyStrikerCardGroup.class.getName());
    @SpireEnum
    public static CardGroup.CardGroupType ExtraDeck;
    @SpireEnum
    public static CardGroup.CardGroupType ExtraDeckMaster;


    @Override
    public List<String> onSave() {
        logger.info("saving...");
        if(AbstractDungeon.player instanceof TheSkyStriker) {
            TheSkyStriker player = (TheSkyStriker) AbstractDungeon.player;
            ArrayList<AbstractCard> group = player.extraDeckMaster.group;
            return group.stream().map(c -> c.cardID).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void onLoad(List<String> cardIDList) {
        logger.info("loading...");
        if(AbstractDungeon.player instanceof TheSkyStriker) {
            TheSkyStriker player = (TheSkyStriker) AbstractDungeon.player;
            for (String id : cardIDList) {
                player.extraDeckMaster.addToTop(CardLibrary.getCard(id));
            }
        }
    }
    @Override
    public Type savedType()
    {
        return new TypeToken<List<String>>(){}.getType();
    }
}
