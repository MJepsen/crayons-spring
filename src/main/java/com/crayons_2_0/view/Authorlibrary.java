package com.crayons_2_0.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;

import com.crayons_2_0.component.CourseEditor;
import com.crayons_2_0.component.CourseEditor.CourseEditorListener;
import com.crayons_2_0.component.Uniteditor;
import com.crayons_2_0.mockup.autorenbereich;
import com.crayons_2_0.service.LanguageService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.crayons_2_0.component.Uniteditor;

@SpringUI
public class Authorlibrary extends VerticalLayout implements View, CourseEditorListener{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String VIEW_NAME = "Authorlibrary";
    ResourceBundle lang = LanguageService.getInstance().getRes();

    /*public Authorlibrary() {
        VerticalLayout aboutContent = new VerticalLayout();
        //aboutContent.setStyleName("about-content");

        // you can add Vaadin components in predefined slots in the custom
        // layout

        setSizeFull();
        setStyleName("about-view");
        setSpacing(true);
        setMargin(true);

       // addComponent(aboutContent);
        //setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
        autorenbereich a = new autorenbereich();
        addComponent(a);
        setExpandRatio(a, 1f);
        addComponent(buildFooter());
    }*/
    
    public Authorlibrary() {
        VerticalLayout content = new VerticalLayout();

        setSizeFull();
        setStyleName("about-view");
        setSpacing(true);
        setMargin(true);

        addComponent(content);
        content.addComponent(buildTitle());
        content.addComponent(buildCoursesTabSheet());
    }
    
    private Component buildTitle() {
        Label title = new Label("Kursübersicht");
        title.addStyleName(ValoTheme.LABEL_H2);
        return title;
    }
    
    private Component buildCoursesTabSheet() {
        TabSheet coursesTabSheet = new TabSheet();
        coursesTabSheet.setSizeFull();
        coursesTabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        coursesTabSheet.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        coursesTabSheet.addComponent(buildCourseTab("Lineare Algebra"));
        coursesTabSheet.addTab(buildAddNewCourseTab());
        return coursesTabSheet;
    }
    
    // TODO: layout with a "constructor" to build a new course
    private Component buildAddNewCourseTab() {
        VerticalLayout addNewCourseTabContent = new VerticalLayout();
        addNewCourseTabContent.setIcon(FontAwesome.PLUS);
        return addNewCourseTabContent;
    }

    private Component buildCourseTab(String title) {
        VerticalLayout tabContent = new VerticalLayout();
        tabContent.setCaption(title);
        tabContent.setSpacing(true);
        tabContent.setMargin(true);
        
        TwinColSelect selectStudents = new TwinColSelect("Select students");
        selectStudents.setRows(10);
        selectStudents.setSizeFull();
        selectStudents.setLeftColumnCaption("List of all students");
        selectStudents.setRightColumnCaption("Participants");
        
        String nonParticipants[] = {"Heidi Klum", "Kate Moss", "Natalia Vodianova", "Cara Delevingne"};
        for (int i=0; i<nonParticipants.length; i++)
            selectStudents.addItem(nonParticipants[i]);
                 
        selectStudents.setImmediate(true);
        tabContent.addComponent(selectStudents);
        
        tabContent.addComponent(buildControlButtons());
        
        return tabContent;
    }
    
    private Component buildControlButtons() {
        HorizontalLayout controlButtons = new HorizontalLayout();
        controlButtons.setMargin(true);
        controlButtons.setSpacing(true);
        
        Button studentView = new Button("Student view");
        controlButtons.addComponent(studentView);
        
        Button graphEditor = new Button("Graph editor");
        controlButtons.addComponent(graphEditor);
        graphEditor.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(CourseEditorView.VIEW_NAME);
                //getUI().getUI().getPage().setLocation(uri);
            }
        });
        
        Button courseDescription = new Button("Course description");
        controlButtons.addComponent(courseDescription);
        
        return controlButtons;
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }


    @Override
    public void titleChanged(String newTitle, CourseEditor editor) {
        // TODO Auto-generated method stub
        
    }  
}
