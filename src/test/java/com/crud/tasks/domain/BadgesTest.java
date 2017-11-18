package com.crud.tasks.domain;

import com.crud.tasks.domain.badges.AttachmentsByType;
import com.crud.tasks.domain.badges.Badges;
import com.crud.tasks.domain.badges.Trello;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BadgesTest {
    @Test
    public void shouldGetBagde() {
        //Given
        Trello trello = new Trello(2, 3);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(1, attachmentsByType);

        //When
        int resusltBagdeVotes = badges.getVotes();
        Trello resultTrello = attachmentsByType.getTrello();
        int trelloBoard = trello.getBoard();
        int trelloCard = trello.getCard();

        //Then
        assertEquals(1, resusltBagdeVotes);
        assertNotNull(resultTrello);
        assertEquals(2, trelloBoard);
        assertEquals(3, trelloCard);
    }
}
