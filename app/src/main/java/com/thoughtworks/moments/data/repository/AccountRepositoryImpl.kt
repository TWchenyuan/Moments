package com.thoughtworks.moments.data.repository

import com.thoughtworks.moments.data.Account
import com.thoughtworks.moments.data.network.AccountAPI
import com.thoughtworks.moments.data.toAccount

class AccountRepositoryImpl(private val accountApi: AccountAPI) : AccountRepository {
  override suspend fun currentAccount(): Account = this.accountApi.getAccount().toAccount()
}
