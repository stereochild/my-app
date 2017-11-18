package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @Mock
    TrelloValidator trelloValidator;

    @Test
    public void shouldValidateBoards() {

        //Given
        TrelloValidator validator = new TrelloValidator();

        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloBoard firstBoard = new TrelloBoard("1", "name", trelloLists);
        TrelloBoard secondBoard = new TrelloBoard("2", "test", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(firstBoard);
        trelloBoards.add(secondBoard);

        //When
        List<TrelloBoard> validateList = validator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1, validateList.size());
        assertFalse(validateList.contains(secondBoard));
        assertTrue(validateList.contains(firstBoard));
    }

    @Test
    public void ShouldValidateCard() {
        //Given
        TrelloCard firstCard = new TrelloCard("Name", "Description", "Up", "1");
        TrelloCard secondCard = new TrelloCard("Card", "Card Description", "Up", "2");

        doNothing().when(trelloValidator).validateCard(firstCard);
        doNothing().when(trelloValidator).validateCard(secondCard);

        //When
        trelloValidator.validateCard(firstCard);
        trelloValidator.validateCard(secondCard);

        //Then
        Mockito.verify(trelloValidator, times(1)).validateCard(firstCard);
        Mockito.verify(trelloValidator, times(1)).validateCard(secondCard);
    }
}
