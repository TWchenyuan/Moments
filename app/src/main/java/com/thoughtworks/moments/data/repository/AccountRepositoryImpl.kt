package com.thoughtworks.moments.data.repository

import com.thoughtworks.moments.data.Account
import com.thoughtworks.moments.data.network.AccountAPI

class AccountRepositoryImpl(accountApi: AccountAPI) : AccountRepository {
  override suspend fun currentAccount(): Account {
    TODO("Not yet implemented")
  }
}
