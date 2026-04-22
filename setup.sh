#!/bin/bash
mkdir -p src/main/entities src/main/ui
cat > src/main/Game.java << 'EOF'
public class Game {
    // TODO
}
EOF
cat > src/main/Board.java << 'EOF'
public class Board {
    // TODO
}
EOF
cat > src/main/Weapon.java << 'EOF'
public class Weapon {
    // TODO
}
EOF
cat > src/main/entities/Entity.java << 'EOF'
package entities;

public abstract class Entity {
    // TODO
}
EOF
cat > src/main/entities/Combatant.java << 'EOF'
package entities;

public abstract class Combatant extends Entity {
    // TODO
    // This exists because we only want players and enemies to have weapons.
}
EOF
cat > src/main/entities/Player.java << 'EOF'
package entities;

public class Player extends Combatant {
    // TODO
}
EOF
cat > src/main/entities/Enemy.java << 'EOF'
package entities;

public class Enemy extends Combatant {
    // TODO
}
EOF
cat > src/main/entities/Item.java << 'EOF'
package entities;

public class Item extends Entity {
    // TODO
}
EOF
cat > src/main/ui/GamePanel.java << 'EOF'
package ui;

public class GamePanel {
    // TODO
    // swing frontend.
}
EOF