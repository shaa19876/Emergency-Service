package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.models.IncidentDBO
import com.example.database.models.ZoneDBO

@Dao
interface DatabaseDAO {

    //zones methods

    @Query("SELECT * FROM ZoneDBO")
    suspend fun getAllZones(): List<ZoneDBO>

    @Query("SELECT * FROM ZoneDBO WHERE id == :idZone")
    suspend fun getZone(idZone: Int): ZoneDBO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllZones(incidents: List<ZoneDBO>)

    @Query("DELETE FROM ZoneDBO")
    suspend fun clearZoneTable()

    //incidents methods

    @Query("SELECT * FROM IncidentDBO")
    suspend fun getAllIncidents(): List<IncidentDBO>

    @Query("SELECT * FROM IncidentDBO WHERE id == :idIncident")
    suspend fun getIncident(idIncident: Int): IncidentDBO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllIncidents(incidents: List<IncidentDBO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncident(incident: IncidentDBO)

    @Query("DELETE FROM IncidentDBO")
    suspend fun clearIncidentTable()

}