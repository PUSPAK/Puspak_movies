package org.demo.lowsproject.domain.usecase.interfaces

interface RemoveFavoriteTvShowByIdUseCase {
    suspend operator fun invoke(id: Int)
}