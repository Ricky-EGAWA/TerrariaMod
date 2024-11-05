package ricky.terrariamod.entity.client.bats;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import ricky.terrariamod.entity.custom.JungleBatEntity;
import java.util.EnumSet;

public class JungleBatAttackGoal extends Goal {
    private final JungleBatEntity jungleBat;
    private LivingEntity target;
    private final double speed;
    private final boolean pauseWhenIdle;
    private final double attackRange = 2.0D; // 攻撃範囲を2ブロックに設定

    public JungleBatAttackGoal(JungleBatEntity jungleBat, double speed, boolean pauseWhenIdle) {
        this.jungleBat = jungleBat;
        this.speed = speed;
        this.pauseWhenIdle = pauseWhenIdle;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        this.target = this.jungleBat.getTarget(); // ターゲットの取得
        return this.target != null && this.target.isAlive();
    }

    @Override
    public void start() {
        this.jungleBat.getNavigation().startMovingTo(this.target, this.speed);
    }

    @Override
    public void stop() {
        this.target = null;
        this.jungleBat.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (this.target != null) {
            this.jungleBat.getLookControl().lookAt(this.target, 30.0F, 30.0F); // ターゲットに視線を合わせる

            // ターゲットへの距離をチェック
            double distanceToTarget = this.jungleBat.squaredDistanceTo(this.target);

            // 一定範囲内で攻撃
            if (distanceToTarget < attackRange * attackRange) {
                this.jungleBat.tryAttack(this.target);
            } else {
                // 範囲外であればターゲットに向かって移動
                this.jungleBat.getNavigation().startMovingTo(this.target, this.speed);
            }
        }
    }
}
