import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int lebar = 600;
    int tinggi = 650;

    JFrame frame = new JFrame("TIC-TAC-TOE -- ICIKIWIR");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String PemainX = "X";
    String PemainO = "0";
    String Pemainnya = PemainX;

    boolean gameOver = false;
    int Giliran = 0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(tinggi, lebar);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TicTacToe - ICIKIWIR");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int a = 0; a < 3; a++){
            for (int b = 0; b < 3; b++){
                JButton tile = new JButton();
                board[a][b] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(Pemainnya);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == ""){
                            tile.setText(Pemainnya);
                            Giliran++;
                            cekPemenang();
                            if (!gameOver){
                                Pemainnya = Pemainnya == PemainX ? PemainO : PemainX;
                                textLabel.setText("Giliran " + Pemainnya);
                            }
                        }
                    }
                });
            }           
        }
    }

    void cekPemenang(){
        //horizontal
        for (int c = 0; c < 3; c++){
            if (board[c][0].getText() == "") continue;

            if (board[c][0].getText() == board[c][1].getText() &&
                board[c][1].getText()== board[c][2].getText()){
                    for (int i = 0; i < 3; i++){
                        setMenang(board[c][i]);
                    }
                    gameOver = true;
                    return;
                }
        }

        //vertikal
        for (int d = 0; d < 3; d++){
            if (board[0][d].getText() == "") continue;

            if (board[0][d].getText() == board[1][d].getText() &&
                board[1][d].getText()== board[2][d].getText()){
                for (int i = 0; i < 3; i++){
                    setMenang(board[i][d]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonal
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++){
                setMenang(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //tidak diagonal
        if (board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][0].getText() &&
        board[0][2].getText() != "") {
            setMenang(board[0][2]);
            setMenang(board[1][1]);
            setMenang(board[2][0]);
            gameOver = true;
        }

        if (Giliran == 9) {
            for (int w = 0; w < 3; w++){
                for (int g = 0; g < 3; g++){
                    setSeri(board[w][g]);
                }
            }
            gameOver = true;
        }
        
    }

    void setMenang(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText("Pemenangnya Adalah " + Pemainnya + "!!!");
    }

    void setSeri(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("SERI BANG!");
    }
}
