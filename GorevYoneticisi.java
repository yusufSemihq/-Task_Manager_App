import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GorevYoneticisi {
    private JFrame frame;
    private JTextField taskField;
    private JComboBox<String> categoryComboBox, priorityComboBox;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;

    private List<Task> tasks;

    public GorevYoneticisi() {
        tasks = new ArrayList<>();

        frame = new JFrame("Görev Yöneticisi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel taskLabel = new JLabel("Görev:");
        taskField = new JTextField();
        JLabel categoryLabel = new JLabel("Kategori:");
        categoryComboBox = new JComboBox<>(new String[]{"Kişisel", "İş", "Alışveriş", "Diğer"});
        JLabel priorityLabel = new JLabel("Öncelik:");
        priorityComboBox = new JComboBox<>(new String[]{"Düşük", "Orta", "Yüksek"});
        JButton addButton = new JButton("Ekle");

        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryComboBox);
        inputPanel.add(priorityLabel);
        inputPanel.add(priorityComboBox);
        inputPanel.add(addButton);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        frame.setVisible(true);
    }

    private void addTask() {
        String taskName = taskField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        String priority = (String) priorityComboBox.getSelectedItem();

        Task task = new Task(taskName, category, priority);
        tasks.add(task);
        listModel.addElement(task.toString());

        taskField.setText("");
    }

    private class Task {
        private String name;
        private String category;
        private String priority;

        public Task(String name, String category, String priority) {
            this.name = name;
            this.category = category;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return name + " (" + category + ", " + priority + ")";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GorevYoneticisi();
            }
        });
    }
}
