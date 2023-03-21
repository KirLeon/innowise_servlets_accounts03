package com.innowise.servlets_task.mapper;

import com.innowise.servlets_task.dto.AccountDTO;
import com.innowise.servlets_task.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

  AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

  AccountDTO entityToDto(Account account);

  Account dtoToEntity(AccountDTO accountDTO);

}
