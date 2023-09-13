package com.isa.grades;

import com.isa.account.User;
import com.isa.account.UserManager;

import java.util.*;

public class GradesManagement {

    List<Integer> gradeList = new ArrayList<>();

    public Integer addGrade(){
        System.out.println("Podaj ocene: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    private Map<Subjects, List<Integer>> fillGrades() {

        Integer grade = addGrade();
        Map<Subjects, List<Integer>> mapGrades = new HashMap<>();
        gradeList.add(grade);
        mapGrades.putIfAbsent(Subjects.POLSKI, new ArrayList<>()) ;
        mapGrades.put(Subjects.POLSKI, gradeList);
        return mapGrades;
        }
    }





