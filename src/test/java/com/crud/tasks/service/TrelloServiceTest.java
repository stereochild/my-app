package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.badges.AttachmentsByType;
import com.crud.tasks.domain.badges.Badges;
import com.crud.tasks.domain.badges.Trello;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "List", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloBoardDto myBoard = new TrelloBoardDto("1", "Board", trelloListDtos);
        trelloBoardDtos.add(myBoard);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> resultList = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(myBoard));
    }

    @Test
    public void testCreateTrelloCard() {
        //Given
        Badges badges = new Badges(1, new AttachmentsByType(new Trello(1, 1)));
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("2", "New card", "newcard.com", badges);
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card", "Description", "Up", "1");

        when(trelloClient.createNewCard(any())).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto myCard = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals("2", myCard.getId());
        assertEquals("New card", myCard.getName());
        assertEquals("newcard.com", myCard.getShortUrl());
        assertEquals(badges, myCard.getBadges());
    }
}
