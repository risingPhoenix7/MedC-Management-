package com.example.medcmanagementapp;

import java.util.ArrayList;
public class MedicalStore
{
    private static double revenueDue;
    private static double revenueGenerated;
    private static ArrayList<Medicine> MedicinesList=new ArrayList();
    void increaseRevenueDue(double d) { revenueDue+=d; }
    void increaseRevenueGenerated(double d) { revenueGenerated+=d; }
    void resetRevenue() { revenueDue=0.0; revenueGenerated=0.0; }
    void addMedicines(ArrayList<Medicine> a)
    {

    }
    void removeMedicines(String s, int a)
    {

    }
}
