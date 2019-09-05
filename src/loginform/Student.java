
package loginform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Student 
{
    public void insertUpdateDeleteStudent(char operation,Integer sno,String name,String address,String gender,String faculty,String program) throws ClassNotFoundException
    {
        Connection con=MyConnection.getConnection();
        PreparedStatement ps;
        if(operation=='i')
        {
            try
            {
                ps=con.prepareStatement("INSERT INTO form_info(Name, Address, Gender, Faculty, Program) VALUES (?,?,?,?,?)");
                
                ps.setString(1,name);
                ps.setString(2,address);
                ps.setString(3,gender);
                ps.setString(4,faculty);
                ps.setString(5,program);
                
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null,"New Student Added");
                }
            }
                catch(SQLException ex)
                        {
                        Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null,ex);
                        }
            
        }
    
               
            if(operation=='u')
        {
            try
            {
                ps=con.prepareStatement("UPDATE `form_info` SET `Name`=?,`Address`=?,`Gender`=?,`Faculty`=?,`Program`=?");
                
                ps.setString(1,name);
                ps.setString(2,address);
                ps.setString(3,gender);
                ps.setString(4,faculty);
                ps.setString(5,program);
                
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null,"Student Data Updated");
                }
            }
                catch(SQLException ex)
                        {
                        Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null,ex);
                        }
            
        }
            
            if(operation=='d')
        {
            
            int YesOrNo = JOptionPane.showConfirmDialog(null,"The Scores will be also Deleted","Delete Student",JOptionPane.OK_CANCEL_OPTION,0);
            if(YesOrNo == 0)
                    {        
                        
                   
            try
            {
                ps=con.prepareStatement("DELETE FROM `form_info` WHERE `Sym`=?");
                ps.setInt(1,sno);
                
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null,"Student Data Deleted");
                }
            }
                catch(SQLException ex)
                        {
                        Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null,ex);
                        }
                    }   
        }
}
    
    public void fillStudentTable(JTable table,String valueToSearch) throws ClassNotFoundException
    {
        try {
            Connection con=MyConnection.getConnection();
            
            
            
            PreparedStatement ps;
            ps=con.prepareStatement("SELECT * FROM `form_info` WHERE CONCAT(`Name`,`Address`,`Faculty`,`Program`)LIKE ?");
            ps.setString(1,"%"+valueToSearch+"%");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next())
            {
                row=new Object[6];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                row[3]=rs.getString(4);
                row[4]=rs.getString(5);
                row[5]=rs.getString(6);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
