package clickme.nocubes.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class BlockTweakInjector implements IClassTransformer {

    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null)
            return null;

        boolean obfuscated = true;

        if (!"ahu".equals(name))
            if (!"net.minecraft.block.Block".equals(name))
                return bytes;
            else
                obfuscated = false;

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, ClassReader.EXPAND_FRAMES);

        MethodNode targetMethod = null;
        for (MethodNode methodNode : classNode.methods)
            if (obfuscated) {
                if ("a".equals(methodNode.name) && "(Lafn;IIILayf;Ljava/util/List;Lqn;)V".equals(methodNode.desc)) {
                    targetMethod = methodNode;
                    break;
                }
            } else if ("addCollisionBoxesToList".equals(methodNode.name)) {
                targetMethod = methodNode;
                break;
            }

        if (targetMethod == null)
            return bytes;

        MethodNode injectedMethod = new MethodNode();

        Label label0 = new Label();
        injectedMethod.visitLabel(label0);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 0);
        injectedMethod.visitMethodInsn(Opcodes.INVOKESTATIC, "clickme/nocubes/NoCubes", "isBlockSoftForCollision",
                obfuscated ? "(Lahu;)Z" : "(Lnet/minecraft/block/Block;)Z");

        Label label1 = new Label();
        injectedMethod.visitJumpInsn(Opcodes.IFEQ, label1);

        Label label2 = new Label();
        injectedMethod.visitLabel(label2);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 0);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 1);
        injectedMethod.visitVarInsn(Opcodes.ILOAD, 2);
        injectedMethod.visitVarInsn(Opcodes.ILOAD, 3);
        injectedMethod.visitVarInsn(Opcodes.ILOAD, 4);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 5);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 6);
        injectedMethod.visitVarInsn(Opcodes.ALOAD, 7);
        injectedMethod.visitMethodInsn(Opcodes.INVOKESTATIC, "clickme/nocubes/SoftBlockRenderer", "inject",
                obfuscated ? "(Lahu;Lafn;IIILayf;Ljava/util/List;Lqn;)V" : "(Lnet/minecraft/block/Block;"
                        + "Lnet/minecraft/world/World;III" + "Lnet/minecraft/util/AxisAlignedBB;" + "Ljava/util/List;"
                        + "Lnet/minecraft/entity/Entity;)V");

        Label label3 = new Label();
        injectedMethod.visitLabel(label3);
        injectedMethod.visitInsn(Opcodes.RETURN);
        injectedMethod.visitLabel(label1);

        targetMethod.instructions.insert(injectedMethod.instructions);

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
