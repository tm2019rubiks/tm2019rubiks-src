package tm2019rubiks.solve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;
import tm2019rubiks.rcube.Move;
import tm2019rubiks.rcube.RCube;
import tm2019rubiks.rcube.RFace;
import tm2019rubiks.utils.Utils;

/**
 *
 * @author estok
 */
public class Solver {

    public static ArrayList<Move> layerWiseSolution(RCube toSolve) {

        RCube cube = toSolve.copy();
        ArrayList<Move> moves = new ArrayList<>();

        //step1: black edges facing up
        //liberateMapU is a map used to know which facelet of the U face has to
        //freed of any already placed black faces, when the algorithm wants to make a move
        HashMap<Move, int[]> liberateMapU = new HashMap<>();
        liberateMapU.put(Move.B, new int[]{0, 1});
        liberateMapU.put(Move.BP, new int[]{0, 1});
        liberateMapU.put(Move.L, new int[]{1, 0});
        liberateMapU.put(Move.LP, new int[]{1, 0});
        liberateMapU.put(Move.R, new int[]{1, 2});
        liberateMapU.put(Move.RP, new int[]{1, 2});
        liberateMapU.put(Move.F, new int[]{2, 1});
        liberateMapU.put(Move.FP, new int[]{2, 1});

        //construct edgeMoves
        //blackEdgeMoves is the map that converts a position of a black edge to a 
        //number of moves.
        //TODO: rewrite this to be read from a file.
        HashMap<String, Move[]> downEdgeMoves = new HashMap<>();
        final int[][] edgeCoords = {{1, 0}, {0, 1}, {1, 2}, {2, 1}};
        for (int i = 0; i < 4; i++) {

            int front = i;
            int left = (i >= 1) ? i - 1 : 3;
            int right = (i <= 2) ? i + 1 : 0;

            //case 1, 0
            int[] key = {i, edgeCoords[0][0], edgeCoords[0][1]};
            Move[] entry = {new Move(left, -1, 1)};
            downEdgeMoves.put(Arrays.toString(key), entry);

            //case 0, 1 
            key = new int[]{i, edgeCoords[1][0], edgeCoords[1][1]};
            entry = new Move[]{new Move(front, 1, 1), new Move(right, 1, 1)};
            downEdgeMoves.put(Arrays.toString(key), entry);

            //case 1, 2
            key = new int[]{i, edgeCoords[2][0], edgeCoords[2][1]};
            entry = new Move[]{new Move(right, 1, 1)};
            downEdgeMoves.put(Arrays.toString(key), entry);

            //case 2, 1
            key = new int[]{i, edgeCoords[3][0], edgeCoords[3][1]};
            entry = new Move[]{new Move(front, 1, 1), new Move(left, -1, 1)};
            downEdgeMoves.put(Arrays.toString(key), entry);
        }
        for (int[] coord : edgeCoords) {
            int[] key = {4, coord[0], coord[1]};
            Move[] entry = {};
            downEdgeMoves.put(Arrays.toString(key), entry);
        }

        downEdgeMoves.put(Arrays.toString(new int[]{5, 0, 1}), new Move[]{Move.F, Move.F});
        downEdgeMoves.put(Arrays.toString(new int[]{5, 1, 0}), new Move[]{Move.L, Move.L});
        downEdgeMoves.put(Arrays.toString(new int[]{5, 1, 2}), new Move[]{Move.R, Move.R});
        downEdgeMoves.put(Arrays.toString(new int[]{5, 2, 1}), new Move[]{Move.B, Move.B});

        //solve the 4 edges
        for (int i = 0; i < 4; i++) {

            //after every edge solved, the cube changes
            int[][] downEdges = cube.getEdges(RFace.INDEX_FACE_DOWN);

            //initialize the currEdge array with null, so when there's no edge left to solve
            //the algorithm stops
            int[] currentEdge = null;

            //look for an edge that needs to be solved ( == not on U Face)
            for (int[] edge : downEdges) {
                if (edge[0] != RFace.INDEX_FACE_UP) {
                    currentEdge = edge;
                    break;
                }
            }
            if (currentEdge == null) {
                System.out.println("EDGES PLACED");
                break;

            }
            //get Move array needed for solving the currentEdge
            Move[] placeEdge = downEdgeMoves.get(Arrays.toString(currentEdge));

            for (Move m : placeEdge) {

                //get which position of U Face has to be freed of down edges
                int[] pos = liberateMapU.get(m);

                //liberate the face from D edges
                while (cube.getFaces()[4].getColors()[pos[0]][pos[1]] == RFace.INDEX_FACE_DOWN) {
                    cube.applyMove(Move.U);
                    moves.add(Move.U);
                }

                //if it's freed, apply the move.
                cube.applyMove(m);
                moves.add(m);
            }

        }

        //step2 : down edges facing down, aligned with center
        //map to know which part of the U face corresponds to which other face
        //so when you have to do the S2 turns, you can know which part of U
        //needs to have the D piece
        HashMap<Integer, int[]> upSideLinks = new HashMap<>();
        upSideLinks.put(RFace.INDEX_FACE_LEFT, new int[]{1, 0});
        upSideLinks.put(RFace.INDEX_FACE_BACK, new int[]{0, 1});
        upSideLinks.put(RFace.INDEX_FACE_RIGHT, new int[]{1, 2});
        upSideLinks.put(RFace.INDEX_FACE_FRONT, new int[]{2, 1});

        //for each face, you have to align the color, and then apply S2
        for (int i = 0; i < 4; i++) {
            RFace currFace = cube.getFace(i);

            //while the colors are not aligned, and the face on the up is not black, 
            //do an U Move
            while (currFace.getColors()[0][1] != currFace.getColorIndex()
                    || cube.getFace(RFace.INDEX_FACE_UP).getColor(upSideLinks.get(i)) != RFace.INDEX_FACE_DOWN) {
                cube.applyMove(Move.U);
                moves.add(Move.U);
            }
            //when aligned, place edge down
            Move placeEdgeDown = new Move(i, 1, 2);
            moves.add(placeEdgeDown);
            cube.applyMove(placeEdgeDown);

        }

        //step3 : place down corners
        //sexy moves to place the corner, based on its position in the U face
        Move[][] sexyMoves = {{Move.F, Move.U, Move.FP, Move.UP},
        {Move.R, Move.U, Move.RP, Move.UP},
        {Move.B, Move.U, Move.BP, Move.UP},
        {Move.L, Move.U, Move.LP, Move.UP}};

        //the element at each index corresponds to the position on the U ord D
        //face of the said index
        //for example, 1 is the down right corner when looking frontally at U
        int[][] uFaceSlots = {{2, 0}, {2, 2}, {0, 2}, {0, 0}};
        int[][] dFaceSlots = {{0, 0}, {0, 2}, {2, 2}, {2, 0}};

        //for all corner slots, 
        for (int i = 0; i < 4; i++) {

            //determine which face is left relative to the slot that we want to fill
            //in
            int left = (i >= 1) ? i - 1 : 3;
            int right = i;

            //get the faces for readability
            RFace leftFace = cube.getFace(left);
            RFace rightFace = cube.getFace(right);
            RFace downFace = cube.getFace(RFace.INDEX_FACE_DOWN);
            RFace upFace = cube.getFace(RFace.INDEX_FACE_UP);

            //coordinates of the facelet to check on the side faces
            //when the inspected layer is D
            int[] rcoords = {2, 0};
            int[] lcoords = {2, 2};
            //when the inspected layer is U
            int[] rucoords = {0, 0};
            int[] lucoords = {0, 2};

            //for the slot i, get the colors of the piece that we want to put in 
            //that place
            int[] cornerPieceColors = {leftFace.getColorIndex(),
                rightFace.getColorIndex(),
                downFace.getColorIndex()};

            //the position of the cubie
            //0 : Face (U or D)
            //1 : the position on the U or D face according to FaceSlots
            int[] pos = new int[2];

            //get position of cubie
            //look for each slot position
            //look in D layer
            for (int search = 0; search < 4; search++) {

                //get faces relative to the slot that's being searched
                int searchLeft = (search >= 1) ? search - 1 : 3;
                int searchRight = search;

                //construct the array with the colors of the corner cubie
                int[] piece = {downFace.getColor(dFaceSlots[search]),
                    cube.getFace(searchLeft).getColor(lcoords),
                    cube.getFace(searchRight).getColor(rcoords)};

                //if the corner piece that's being inspected is the same as
                //the one we're looking for, store its data
                if (Utils.setEquals(cornerPieceColors, piece)) {
                    pos[0] = RFace.INDEX_FACE_DOWN;
                    pos[1] = search;
                }
            }

            //look in U layer
            //same as the loop before
            for (int search = 0; search < 4; search++) {

                //get faces relative to the slot that's being searched
                int searchLeft = (search >= 1) ? search - 1 : 3;
                int searchRight = search;

                //construc the array with the colors of the corner cubie
                int[] piece = {upFace.getColor(uFaceSlots[search]),
                    cube.getFace(searchLeft).getColor(lucoords),
                    cube.getFace(searchRight).getColor(rucoords)};

                if (Utils.setEquals(cornerPieceColors, piece)) {
                    pos[0] = RFace.INDEX_FACE_UP;
                    pos[1] = search;

                }
            }

            //if slot already contains piece, pass
            //else, do the following
            if (!(leftFace.getColorIndex() == leftFace.getColor(lcoords)
                    && rightFace.getColorIndex() == rightFace.getColor(rcoords)
                    && downFace.getColor(dFaceSlots[i]) == RFace.INDEX_FACE_DOWN)) {

                //if the piece that goes into the i slot is in the down fase
                if (pos[0] == RFace.INDEX_FACE_DOWN) {

                    //if the piece isn't in the right position
                    if (i != pos[0]) {

                        //put it in the top face using a sexy move
                        for (Move m : sexyMoves[pos[1]]) {
                            cube.applyMove(m);
                            moves.add(m);
                        }
                        //then turn the U face until it's sitting above the slot
                        //it's supposed to go in
                        while (!Utils.setEquals(
                                new int[]{upFace.getColor(uFaceSlots[i]),
                                    leftFace.getColor(lucoords),
                                    rightFace.getColor(rucoords)}, cornerPieceColors)) {
                            cube.applyMove(Move.U);
                            moves.add(Move.U);
                        }
                        //then, until the face is solved, do sexy moves
                        while (!(leftFace.getColorIndex() == leftFace.getColor(lcoords)
                                && rightFace.getColorIndex() == rightFace.getColor(rcoords)
                                && downFace.getColor(dFaceSlots[i]) == RFace.INDEX_FACE_DOWN)) {

                            for (Move m : sexyMoves[i]) {
                                cube.applyMove(m);
                                moves.add(m);
                            }

                        }
                    }

                } //else, if the piece is already in the U face
                else {
                    //rotate the U face until the piece is above the slot it needs to go in

                    while (!Utils.setEquals(
                            new int[]{upFace.getColor(uFaceSlots[i]),
                                leftFace.getColor(lucoords),
                                rightFace.getColor(rucoords)}, cornerPieceColors)) {
                        cube.applyMove(Move.U);
                        moves.add(Move.U);
                    }
                    //spam sexy moves until solved
                    while (!(leftFace.getColorIndex() == leftFace.getColor(lcoords)
                            && rightFace.getColorIndex() == rightFace.getColor(rcoords)
                            && downFace.getColor(dFaceSlots[i]) == RFace.INDEX_FACE_DOWN)) {

                        for (Move m : sexyMoves[i]) {
                            cube.applyMove(m);
                            moves.add(m);
                        }

                    }

                }

            }

        }

        //step4 : place middle edges
        for (int i = 0; i < 4; i++) {

        }

        //step5 : U cross
        //step6 : LL place edges
        //step7 : LL orient them
        //step8 : LL finalize
        //TODO finish this method
        moves.forEach((Move m) -> System.out.print(m + " "));
        System.out.println("\n" + moves.size());
        return moves;
    }
}
