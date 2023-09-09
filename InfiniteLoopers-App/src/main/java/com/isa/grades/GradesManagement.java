package com.isa.grades;

import java.util.*;

public class GradesManagement {

    List<Integer> polishGradeList = new ArrayList<>();

    public Integer addGrade(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    private Map<Subjects, List<Integer>> fillGradesPolish() {

        Integer grade = addGrade();

       Map<Subjects, List<Integer>> mapGrades = new HashMap<>();
        polishGradeList.add(grade);
        mapGrades.putIfAbsent(Subjects.POLSKI, new ArrayList<>()) ;

        mapGrades.put(Subjects.POLSKI, polishGradeList);

        return mapGrades;
        }


    public Map<String, Map<Subjects, List<Integer>>> fillUserMap() {
        Map<Subjects, List<Integer>> map = fillGradesPolish();
        String userID = "2";

        Map<String, Map<Subjects,List<Integer>>> mapSubjectAndGrades = new HashMap<>();
        mapSubjectAndGrades.putIfAbsent(userID, map) ;
        System.out.println(mapSubjectAndGrades);
        return mapSubjectAndGrades;
    }



    }

