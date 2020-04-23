package com.jhurtado.lastfm.ui.artistlist

import com.jhurtado.lastfm.UseCaseHandler
import com.jhurtado.lastfm.ui.artistlist.domain.GetArtistList
import com.jhurtado.lastfm.ui.artistlist.domain.SaveArtistList

/**
 * @author jhurtado
 * Date: 22/04/20
 * LasfFM test for Valid.com
 */
class ArtistListViewModel (val useCaseHandler: UseCaseHandler,
                           val getArtistList: GetArtistList,
                           val saveArtistList: SaveArtistList)