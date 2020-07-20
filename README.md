# Prueba para Valid.com
Por Juan Hurtado https://www.linkedin.com/in/juanchossb

# Map
### - data
    - database
        -dao
            ArtistDao.kt -> Dao with querys for inserting and retrieving artists
            TrackDao -> Dao with querys for inserting and retrieving tracks
        AppDatabase.kt -> Room database
        Converters.kt -> Type converters for saving information in database
###    -model
    Artists.kt -> Artist object
    Attributes.kt -> Attributes object
    BaseObject.kt -> Base object
    Image.kt -> Image object
    Track.kt -> track object
###    -response
    ArtistListResponse.kt -> Respoonse retrieved from retrofit service
    TrackListResponse.kt -> Respoonse retrieved from retrofit service
###    -source
    RetrofitFactory.kt -> Factory for creating and configuring retrofit service
    RetrofitService.kt -> Retrofit interface contains getTracks and getArtists methods
## - ui
    - artistlist
        ArtistListFragment.kt -> Fragment that shows Artist list
        ArtistlistViewModel -> Viewmodel for ArtisListFragment
        ArtistListAdapter -> PagedAdapter for recyclerview
    - tracklist
        TrackListFragment.kt -> Fragment that shows Track list
        TrackListViewModel -> Viewmodel for TrackListFragment
        TrackListAdapter -> PagedAdapter for recyclerview
    BaseListFragment.kt -> Base fragment for showing lists
    ListTabActivity ->  MainActivity with tabs and a viewpager
 ### - utils
    Injections.kt -> Dependency injections
    PicassoCircleTranseform.kt -> Transform object to convert picasso images into circular images
 ApplicationDelegate.kt -> Provides application context and checks for internet connection

### Librerias

Para este demo se usaron las siguientes librerias:

* [Retrofit] - Api interface
* [Picasso] - Image manager
* [Room] - SQLite database
* [OkHTTP] - HTTP client
* [Robolectric] - Unit tests
* [Mockito] - Unit tests
* [Firebase] - crashlytics


