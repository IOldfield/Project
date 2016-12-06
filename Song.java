import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

public class Song
{
   
    public int SongID;
    public String SongName;
    public int ReleaseID;

   
    public Song(int id, String SongName, int ReleaseID)
    {
        this.SongID = SongID;        
        this.SongName = SongName;
        this.ReleaseID = ReleaseID;
    }

   
    @Override public String toString()
    {
        return SongName;
    }
    
    public static void readAll(List<Song> list)
    {
        list.clear();       
        
        PreparedStatement statement = Application.SongsDatabase.newStatement("SELECT SongId, SongName, ReleaseID FROM Songs ORDER BY SongId"); 
        
        if (statement != null)     
        {
            ResultSet results = Application.SongsDatabase.runQuery(statement);       

            if (results != null)        
            {
                try {                              
                    while (results.next()) {                                               
                        list.add( new Song(results.getInt("SongID"), results.getString("SongName"), results.getInt("ReleaseID")));
                    }
                }
                catch (SQLException resultsexception)       
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public static Song getById(int SongID)
    {
        Song Song = null;

        PreparedStatement statement = Application.SongsDatabase.newStatement("SELECT SongID, SongName, ReleaseID FROM Songs WHERE id = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setInt(1, SongID);
                ResultSet results = Application.SongsDatabase.runQuery(statement);

                if (results != null)
                {
                    Song = new Song(results.getInt("SongID"), results.getString("SongName"), results.getInt("ReleaseID"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return Song;
    }

    public static void deleteById(int SongID)
    {
        try 
        {

            PreparedStatement statement = Application.SongsDatabase.newStatement("DELETE FROM Songs WHERE SongID = ?");             
            statement.setInt(1, SongID);

            if (statement != null)
            {
                Application.SongsDatabase.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

    }
    
    public void save()    
    {
        PreparedStatement statement;

        try 
        {

            if (SongID == 0)
            {

                statement = Application.SongsDatabase.newStatement("SELECT SongID FROM Songs ORDER BY SongID DESC");             

                if (statement != null)	
                {
                    ResultSet results = Application.SongsDatabase.runQuery(statement);
                    if (results != null)
                    {
                        SongID = results.getInt("SongID") + 1;
                    }
                }

                statement = Application.SongsDatabase.newStatement("INSERT INTO Songs (SongID, SongName, ReleaseID) VALUES (?, ?, ?)");             
                statement.setInt(1, SongID);
                statement.setString(2, SongName);
                statement.setInt(3, ReleaseID);         

            }
            else
            {
                statement = Application.SongsDatabase.newStatement("UPDATE Songs SET SongName = ?, ReleaseID = ? WHERE SongID = ?");             
                statement.setString(1, SongName);
                statement.setInt(2, ReleaseID);   
                statement.setInt(3, SongID);
            }

            if (statement != null)
            {
                Application.SongsDatabase.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

    }

}