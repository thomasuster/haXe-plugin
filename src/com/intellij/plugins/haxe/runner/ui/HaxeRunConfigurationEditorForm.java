package com.intellij.plugins.haxe.runner.ui;

import com.intellij.execution.ui.ConfigurationModuleSelector;
import com.intellij.ide.util.TreeFileChooser;
import com.intellij.ide.util.TreeFileChooserFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.haxe.HaxeFileType;
import com.intellij.plugins.haxe.runner.HaxeApplicationConfiguration;
import com.intellij.psi.PsiFile;
import com.intellij.ui.RawCommandLineEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HaxeRunConfigurationEditorForm extends SettingsEditor<HaxeApplicationConfiguration> {
    private DefaultComboBoxModel modulesModel;

    private JPanel component;
    private RawCommandLineEditor appArguments;
    private TextFieldWithBrowseButton applicationName;
    private JComboBox comboModules;

    private ConfigurationModuleSelector moduleSelector;
    private Project project;

    public HaxeRunConfigurationEditorForm(Project project) {
        this.project = project;
        addActionListeners();
    }

    public void addActionListeners() {
        applicationName.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                TreeFileChooser fileChooser = TreeFileChooserFactory.getInstance(project).createFileChooser("haXe Application Chooser",
                        null,
                        HaxeFileType.HAXE_FILE_TYPE,
                        new TreeFileChooser.PsiFileFilter() {
                            public boolean accept(PsiFile file) {
                                return true;
                            }
                        });

                fileChooser.showDialog();

                PsiFile selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    setChosenFile(selectedFile.getVirtualFile());
                }
            }
        });
    }

    private void setChosenFile(VirtualFile virtualFile) {
        applicationName.setText(virtualFile.getPath());
    }

    @Override
    protected void resetEditorFrom(HaxeApplicationConfiguration configuration) {
        applicationName.setText(configuration.getMainClass());
        moduleSelector.reset(configuration);
    }

    @Override
    protected void applyEditorTo(HaxeApplicationConfiguration configuration) throws ConfigurationException {
        configuration.setMainClass(applicationName.getText());
        moduleSelector.applyTo(configuration);
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        moduleSelector = new ConfigurationModuleSelector(project, comboModules);
        return component;
    }

    @Override
    protected void disposeEditor() {
        component.setVisible(false);
    }
}
