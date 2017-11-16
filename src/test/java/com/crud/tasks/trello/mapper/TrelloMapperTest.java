package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    @Test
    public void testMapToBoards() {
        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloListDto> listDtos = new ArrayList<>();
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new TrelloBoardDto("001", "TestBoard", listDtos));

        List<TrelloList> lists = new ArrayList<>();
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(new TrelloBoard("001", "TestBoard", lists));

        //When
        List<TrelloBoard> trelloBoards = mapper.mapToBoards(boardDtos);

        //Then
        Assert.assertEquals(1, trelloBoards.size());
        Assert.assertEquals("001", trelloBoards.get(0).getId());
        Assert.assertEquals("TestBoard", trelloBoards.get(0).getName());
        Assert.assertEquals(boards.get(0).getLists(), trelloBoards.get(0).getLists());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloList> lists = new ArrayList<>();
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(new TrelloBoard("01", "test_board", lists));

        List<TrelloListDto> listDtos = new ArrayList<>();
        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new TrelloBoardDto("01", "test_borad", listDtos));

        //When
        List<TrelloBoardDto> trelloBoardDtos = mapper.mapToBoardsDto(boards);

        //Then
        Assert.assertEquals(1, trelloBoardDtos.size());
        Assert.assertEquals("01", trelloBoardDtos.get(0).getId() );
        Assert.assertEquals("test_board", trelloBoardDtos.get(0).getName());
        Assert.assertEquals(boardDtos.get(0).getLists(), trelloBoardDtos.get(0).getLists());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(new TrelloListDto("1", "TestList", true));

        //When
        List<TrelloList> trelloLists = mapper.mapToList(listDtos);

        //Then
        Assert.assertEquals(1, trelloLists.size());
        Assert.assertEquals("1", trelloLists.get(0).getId());
        Assert.assertEquals("TestList", trelloLists.get(0).getName());
        Assert.assertTrue(trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloMapper mapper = new TrelloMapper();

        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("10", "test_list", false));

        //When
        List<TrelloListDto> trelloListDtos = mapper.mapToListDto(lists);

        //Then
        Assert.assertEquals(1, trelloListDtos.size());
        Assert.assertEquals("10", trelloListDtos.get(0).getId());
        Assert.assertEquals("test_list", trelloListDtos.get(0).getName());
        Assert.assertFalse(trelloListDtos.get(0).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloCardDto cardDto = new TrelloCardDto("Something", "important", "Up", "1");

        //When
        TrelloCard trelloCard = mapper.mapToCard(cardDto);

        //Then
        Assert.assertEquals("Something", trelloCard.getName());
        Assert.assertEquals("important", trelloCard.getDescription());
        Assert.assertEquals("Up", trelloCard.getPos());
        Assert.assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloCard card = new TrelloCard("Something else", "also important", "Up", "2");

        //When
        TrelloCardDto trelloCardDto = mapper.mapToCardDto(card);

        //Then
        Assert.assertEquals("Something else", trelloCardDto.getName());
        Assert.assertEquals("also important", trelloCardDto.getDescription());
        Assert.assertEquals("Up", trelloCardDto.getPos());
        Assert.assertEquals("2", trelloCardDto.getListId());
    }
}
