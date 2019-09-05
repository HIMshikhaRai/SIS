
package loginform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Course 
{
  public void insertUpdateDeleteStudent(char operation,Integer Sym,String Label,Integer hours) throws ClassNotFoundException
    {
        Connection con=MyConnection.getConnection();
        PreparedStatement ps;
        if(operation=='i')
        {
         
            try {
                ps=con.prepareStatement("INSERT INTO `course`(`Label`, `Hours`) VALUES (?,?)");
            
            
                
                ps.setString(1,Label);
                ps.setInt(2,hours);
              
                
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null,"New Course Added");
                }
        }
        catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
              
        }
    
        }               
           if(operation=='u')
        {

                try {
                    ps=con.prepareStatement("UPDATE `course` SET `Label`=,`Hours`=? WHERE `Sym`=?");
                    ps.setInt(0,Sym);
                    ps.setString(1,Label);
                    ps.setInt(2,hours);
                    
                    
                    if(ps.executeUpdate()>0)
                    {
                        JOptionPane.showMessageDialog(null,"Course Updated");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
        }
            
            if(operation=='d')
        {

                try {
                    ps=con.prepareStatement("DELETE FROM `course` WHERE `Sym`=?");
                    ps.setInt(1,Sym);
                    
                    if(ps.executeUpdate()>0)
                    {
                        JOptionPane.showMessageDialog(null,"Course Deleted");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
                }

            
}
    }    
   public Boolean isCourseExist(String courseName) throws ClassNotFoundException
   {
       boolean isExist=false;
      try {
          Connection con=MyConnection.getConnection();
          PreparedStatement ps;
          
          
          
          
          
          ps=con.prepareStatement("SELECT * FROM `course` WHERE `Label`=?");
          ps.setString(1,courseName);
          
          ResultSet rs=ps.executeQuery();
          if(rs.next())
          {
              isExist=true;
          }
          
          
         
      } catch (SQLException ex) {
          Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
      }
      return isExist;
      
   }
   
public void fillCourseTable(JTable table) throws ClassNotFoundException
    {
      try {
          Connection con=MyConnection.getConnection();
          
          
          
          PreparedStatement ps;
          ps=con.prepareStatement("SELECT * FROM `course`");
          
          ResultSet rs=ps.executeQuery();
          DefaultTableModel model=(DefaultTableModel)table.getModel();
          Object[] row;
          while(rs.next())
          {
              row=new Object[3];
              row[0]=rs.getInt(1);
              row[1]=rs.getString(2);
              row[2]=rs.getInt(3);
              
              model.addRow(row);
          }
      } catch (SQLException ex) {
          Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
      }
 
    }

    int getCourseId(String courseLabel) throws ClassNotFoundException
    {
        int courseId=0;
      try {
          
          Connection con=MyConnection.getConnection();
          PreparedStatement ps;
          boolean isExist=false;
          
          
          
          
          ps=con.prepareStatement("SELECT * FROM `course` WHERE `Label`=?");
          ps.setString(1,courseLabel);
          
          ResultSet rs=ps.executeQuery();
          if(rs.next())
          {
              courseId=rs.getInt("Sym");
          }
          
          
          
          
          
      } catch (SQLException ex) {
          Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return courseId;
    }
    public void fillCourseCombo(JComboBox combo) throws ClassNotFoundException
    {
      try {
          Connection con=MyConnection.getConnection();
          

          
          PreparedStatement ps;
          ps=con.prepareStatement("SELECT * FROM `course`");
          
          ResultSet rs=ps.executeQuery();

          
          DefaultTableModel model=(DefaultTableModel)combo.getModel();
          
          while(rs.next())
          {
              combo.addItem(rs.getString(2));
          }
      } catch (SQLException ex) {
          Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
    
}

