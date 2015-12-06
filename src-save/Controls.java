import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Controls implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener {// the interfaces and controls.
	private Game game;

	public Controls(Game game) {
		this.game = game;
	};

	public void keyPressed(KeyEvent ev) {
		// System.out.println("Pressed "+ev.getKeyChar()+" "+ev.getKeyCode());
		if (ev.getKeyCode() == ev.VK_P) {// P
			game.setPIsDown(true);
		}
		if (ev.getKeyCode() == 37) {// left
			game.setLeftIsDown(true);
		} else if (ev.getKeyCode() == 38) {// up
			game.setUpIsDown(true);
		} else if (ev.getKeyCode() == 39) {// right
			game.setRightIsDown(true);
		} else if (ev.getKeyCode() == 40) {// down
			game.setDownIsDown(true);
		}
		if (ev.getKeyCode() == ev.VK_A) {// left
			game.setAIsDown(true);
		} else if (ev.getKeyCode() == ev.VK_Z) {// up
			game.setZIsDown(true);
		} else if (ev.getKeyCode() == ev.VK_D) {// right
			game.setDIsDown(true);
		} else if (ev.getKeyCode() == ev.VK_S) {// down
			game.setSIsDown(true);
		}
		if (ev.getKeyCode() == 27) {// esc
			System.exit(1);
		}
		if (ev.getKeyCode() == 32) {// space
			// game.createBullet();
			game.setSpaceIsDown(true);
		}
		if (ev.getKeyCode() == ev.VK_CONTROL) {// space
			// game.createBullet();
			game.setCtrIsDown(true);
		}
		if (ev.getKeyCode() == ev.VK_C) {
		}
	}

	public void keyReleased(KeyEvent ev) {
		// System.out.println("Released "+ev.getKeyChar()+" "+ev.getKeyCode());
		if (ev.getKeyCode() == 37) {// left
			game.setLeftIsDown(false);
		} else if (ev.getKeyCode() == 38) {// up
			game.setUpIsDown(false);
		} else if (ev.getKeyCode() == 39) {// right
			game.setRightIsDown(false);
		} else if (ev.getKeyCode() == 40) {// down
			game.setDownIsDown(false);
		}
		if (ev.getKeyCode() == ev.VK_P) {// P
			game.setPIsDown(false);
		}
		if (ev.getKeyCode() == ev.VK_A) {// left
			game.setAIsDown(false);
		} else if (ev.getKeyCode() == ev.VK_Z) {// up
			game.setZIsDown(false);
		} else if (ev.getKeyCode() == ev.VK_D) {// right
			game.setDIsDown(false);
		} else if (ev.getKeyCode() == ev.VK_S) {// down
			game.setSIsDown(false);
		}
		if (ev.getKeyCode() == 32) {// space
			// game.createBullet();
			game.setSpaceIsDown(false);
		}
		if (ev.getKeyCode() == ev.VK_CONTROL) {// space
			// game.createBullet();
			game.setCtrIsDown(false);
		}
	}

	public void keyTyped(KeyEvent ev) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}
}