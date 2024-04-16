package com.thoughtworks.moments.data.repository

import com.thoughtworks.moments.data.Account

interface AccountRepository {
  suspend fun currentAccount(): Account
}
