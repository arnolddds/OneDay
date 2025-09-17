package com.sobolev.wocab.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.sobolev.wocab.data.remote.DailyApi
import com.sobolev.wocab.data.mapper.toDomain
import com.sobolev.wocab.domain.model.DailyContent
import com.sobolev.wocab.domain.repository.DailyRepository
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import retrofit2.HttpException

@RequiresApi(Build.VERSION_CODES.O)
class DailyRepositoryImpl @Inject constructor(
    private val api: DailyApi
) : DailyRepository {
    override suspend fun getDaily(date: LocalDate?): DailyContent =
        safeCall {
            val dateParam = date?.format(DateTimeFormatter.ISO_LOCAL_DATE)
            api.getDaily(dateParam).toDomain()
        }

    override suspend fun getDailyRange(start: LocalDate, end: LocalDate): List<DailyContent> =
        safeCall {
            val s = start.format(DateTimeFormatter.ISO_LOCAL_DATE)
            val e = end.format(DateTimeFormatter.ISO_LOCAL_DATE)
            api.getDailyRange(s, e).map { it.toDomain() }
        }

    private inline fun <T> safeCall(block: () -> T): T {
        try {
            return block()
        } catch (e: IOException) {
            throw RuntimeException("Нет соединения или таймаут")
        } catch (e: HttpException) {
            val code = e.code()
            throw RuntimeException("Ошибка сервера ($code)")
        } catch (e: Exception) {
            throw RuntimeException(e.message ?: "Неизвестная ошибка")
        }
    }
}


