package Projectjava;

import java.util.ArrayList;

abstract class Emplooye {
    private String name;
    private int id;


public Emplooye(String name, int id ){
    this.name=name;
    this.id=id;
}
 public String getName(){
    return name;
 }
 public int getId(){
    return id;
 }
abstract  double calculateSalery();
@Override
public String toString(){
    return "Employee [name="+name+", id="+id+", calculateSalery="+calculateSalery()+"] ";
}
     
    
}
class FulltimeEmployee extends Emplooye{
    private double monthelysal;
  public  FulltimeEmployee(String name, int id,double monthelysal){
    super(name,id);
    this.monthelysal=monthelysal;

    }
  @Override
  public double calculateSalery(){
    return monthelysal;

  }

}
class ParttimeEmployee extends Emplooye{
    private  int hourworked;
    private int hourrate;
    public ParttimeEmployee(String name ,int id,int hourworked,int hourrate){
        super(name,id);
        this.hourrate=hourrate;
        this.hourworked=hourworked;

    }
    @Override
    public double calculateSalery(){
        return  hourrate*hourworked;
    }
}
   class Payroll {
    private ArrayList <Emplooye> employeeList;
    public Payroll(){
        employeeList=new ArrayList<Emplooye>();
    }
    public void addEmployee(Emplooye employee){
        employeeList.add(employee);

    }
    public void removeEmployee(int id){
        Emplooye remooveemployee=null;
        for(Emplooye employee:employeeList){
            if(employee.getId()==id){
                employee=remooveemployee;
                break;
            }
        }


    }
    public void displayEmployee(){
        for(Emplooye employee:employeeList){
            System.out.println(employee);
        }
    }
    
   
  
   
   
    public static void main(String[] args) {
        Payroll payrollemployee=new Payroll();
        
        FulltimeEmployee empl1 = new FulltimeEmployee("Neha", 1, 50000);
        ParttimeEmployee empl2 = new ParttimeEmployee("Nehu", 2, 3, 2000);

    payrollemployee.addEmployee(empl1);
    payrollemployee.addEmployee(empl2);
    payrollemployee.displayEmployee();

    }
}

   