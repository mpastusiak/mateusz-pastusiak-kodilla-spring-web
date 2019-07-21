package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("The Board no 1", "1", new ArrayList<>());
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("The Board no 2", "2", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        assertEquals(2, trelloBoards.size());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("The Board no 1", "1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("The Board no 2", "2", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(2, trelloBoardsDto.size());
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "The List no 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "The List no 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "The List no 3", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        trelloListsDto.add(trelloListDto3);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        assertEquals(3, trelloLists.size());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "The List no 1", false);
        TrelloList trelloList2 = new TrelloList("2", "The List no 2", false);
        TrelloList trelloList3 = new TrelloList("3", "The List no 3", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(3, trelloListsDto.size());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card no 1", "This is the Card", "1", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("This is the Card", trelloCard.getDescription());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card no 2", "This is the second Card", "2", "2");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("This is the second Card", trelloCardDto.getDescription());
    }

}
