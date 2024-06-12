package com.rmilab.service;

import com.rmilab.dto.Interaction;
import com.rmilab.dto.InteractionType;
import org.biojava.nbio.structure.*;

import org.biojava.nbio.structure.contact.AtomContact;
import org.biojava.nbio.structure.contact.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

import org.biojava.nbio.structure.contact.AtomContactSet;

@Service
public class InteractionService {
    /*
    public List<String>findInteraction(String proteinFilePath,List<String>ligandPaths){
        List<String> interactionResults = new ArrayList<>();
        try{
            Structure proteinStructure = StructureIO.getStructure(String.valueOf(proteinFilePath));

            for (String ligandFilePath : ligandPaths) {
                Structure ligandStructure = StructureIO.getStructure(ligandFilePath);
                int pIndex = 0;
                for (Chain proteinChain : proteinStructure.getChains()) {
                    int lIndex = 0;
                    for (Chain ligandChain : ligandStructure.getChains()) {
                        AtomContactSet contacts = StructureTools.getAtomsInContact(proteinChain, ligandChain, 4.0, true);
                        StringBuilder interactionsForChains = new StringBuilder();

                        // Append protein and ligand chain indices
                        interactionsForChains.append("Interactions between protein chain index ")
                                .append(pIndex)
                                .append(" and ligand chain index ")
                                .append(lIndex)
                                .append(":\n");
                        // Iterate over contacts and append interaction details
                        for (AtomContact contact : contacts) {
                            Pair<Atom> atomPair = contact.getPair();
                            Atom atom1 = atomPair.getFirst();
                            Atom atom2 = atomPair.getSecond();
                            double distance = contact.getDistance();

                            // Append interaction details to the StringBuilder
                            interactionsForChains.append(atom1.getName()).append(" (").append(atom1.getGroup().getResidueNumber().getSeqNum())
                                    .append(") interacts with ").append(atom2.getName()).append(" (")
                                    .append(atom2.getGroup().getResidueNumber().getSeqNum()).append(") at distance ")
                                    .append(distance).append(" Å\n");
                        }
                        interactionResults.add(interactionsForChains.toString());
                        lIndex++;
                    }//lig chain
                    pIndex++;
                }//protein chain


            }//ligandfile

        }catch(Exception e){

        }
        return interactionResults;
    }

    public List<String> analyzeInteractions1(String proteinFilePath, List<String> ligandFilePaths) {
        List<String> interactionResults = new ArrayList<>();
        try {
            // Load the protein structure
            Structure proteinStructure = StructureIO.getStructure(proteinFilePath);

            for (String ligandFilePath : ligandFilePaths) {
                // Load the ligand structure
                Structure ligandStructure = StructureIO.getStructure(ligandFilePath);

                // Iterate over all chains in the protein structure
                int pIndex = 0;
                for (Chain proteinChain : proteinStructure.getChains()) {
                    // Iterate over all chains in the ligand structure
                    int lIndex = 0;
                    for (Chain ligandChain : ligandStructure.getChains()) {
                        // Calculate interactions between the current protein chain and the current ligand chain
                        AtomContactSet contacts = StructureTools.getAtomsInContact(proteinChain, ligandChain, 4.0, true);

                        // Create a string to hold interaction details for this pair of chains
                        StringBuilder interactionsForChains = new StringBuilder();

                        // Append protein and ligand chain indices
                        interactionsForChains.append("Interactions between protein chain index ")
                                .append(pIndex)
                                .append(" and ligand chain index ")
                                .append(lIndex)
                                .append(":\n");

                        // Iterate over contacts and append interaction details
                        for (AtomContact contact : contacts) {
                            Pair<Atom> atomPair = contact.getPair();
                            Atom atom1 = atomPair.getFirst();
                            Atom atom2 = atomPair.getSecond();
                            double distance = contact.getDistance();

                            // Append interaction details to the StringBuilder
                            interactionsForChains.append(atom1.getName()).append(" (").append(atom1.getGroup().getResidueNumber().getSeqNum())
                                    .append(") interacts with ").append(atom2.getName()).append(" (")
                                    .append(atom2.getGroup().getResidueNumber().getSeqNum()).append(") at distance ")
                                    .append(distance).append(" Å\n");
                        }

                        // Add interactions for this pair of chains to the results list
                        interactionResults.add(interactionsForChains.toString());

                        lIndex++;
                    }
                    pIndex++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return interactionResults;
    }

    //with coordinates
    public List<String> analyzeInteractions2(String proteinFilePath, List<String> ligandFilePaths) {
        List<String> interactionResults = new ArrayList<>();
        try {
            Structure proteinStructure = StructureIO.getStructure(proteinFilePath);

            for (String ligandFilePath : ligandFilePaths) {
                Structure ligandStructure = StructureIO.getStructure(ligandFilePath);

                for (Chain proteinChain : proteinStructure.getChains()) {
                    for (Chain ligandChain : ligandStructure.getChains()) {
                        AtomContactSet contacts = StructureTools.getAtomsInContact(proteinChain, ligandChain, 4.0, true);

                        for (AtomContact contact : contacts) {
                            Atom atom1 = contact.getPair().getFirst();
                            Atom atom2 = contact.getPair().getSecond();
                            double distance = contact.getDistance();

                            // Getting coordinates
                            double[] coords1 = atom1.getCoords();
                            double[] coords2 = atom2.getCoords();

                            // Format interaction result including coordinates
                            String interactionResult = String.format(
                                    "Interaction between %s (%d) at [%f, %f, %f] and %s (%d) at [%f, %f, %f] with distance %f Å",
                                    atom1.getName(), atom1.getGroup().getResidueNumber().getSeqNum(), coords1[0], coords1[1], coords1[2],
                                    atom2.getName(), atom2.getGroup().getResidueNumber().getSeqNum(), coords2[0], coords2[1], coords2[2],
                                    distance
                            );

                            interactionResults.add(interactionResult);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return interactionResults;
    }
    public List<Map<String, Object>> analyzeInteractions3(String proteinFilePath, List<String> ligandFilePaths) {
        List<Map<String, Object>> interactionResults = new ArrayList<>();
        try {
            Structure proteinStructure = StructureIO.getStructure(proteinFilePath);

            for (String ligandFilePath : ligandFilePaths) {
                Structure ligandStructure = StructureIO.getStructure(ligandFilePath);

                for (Chain proteinChain : proteinStructure.getChains()) {
                    for (Chain ligandChain : ligandStructure.getChains()) {
                        AtomContactSet contacts = StructureTools.getAtomsInContact(proteinChain, ligandChain, 4.0, true);

                        for (AtomContact contact : contacts) {
                            Atom atom1 = contact.getPair().getFirst();
                            Atom atom2 = contact.getPair().getSecond();
                            double distance = contact.getDistance();

                            // Getting coordinates
                            double[] coords1 = atom1.getCoords();
                            double[] coords2 = atom2.getCoords();

                            Map<String, Object> interactionResult = new HashMap<>();
                            interactionResult.put("atom1", atom1.getName());
                            interactionResult.put("resNum1", atom1.getGroup().getResidueNumber().getSeqNum());
                            interactionResult.put("coords1", coords1);
                            interactionResult.put("atom2", atom2.getName());
                            interactionResult.put("resNum2", atom2.getGroup().getResidueNumber().getSeqNum());
                            interactionResult.put("coords2", coords2);
                            interactionResult.put("distance", distance);

                            // Determine interaction type
                            String interactionType = determineInteractionType(atom1, atom2, distance);
                            interactionResult.put("type", interactionType);


                            interactionResults.add(interactionResult);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return interactionResults;
    }
    private String determineInteractionType2(Atom atom1, Atom atom2, double distance) {
        // This is a placeholder function. You need to implement the logic to determine
        // the interaction type based on atom properties and distances.
        // For example:
        if (distance < 3.5) {
            // This could be a hydrogen bond if one of the atoms is a hydrogen donor/acceptor
            // Further checks needed based on atom names and residue properties
            return "hydrogenBond";
        } else if (distance >= 3.5 && distance < 5.0) {
            // Other interaction types based on distance or atom/residue properties
            return "hydrophobic";
        }
        // Default to "unknown" or other interaction types as necessary
        return "unknown";
    }


    public List<Interaction> analyzeInteractions4(String proteinFilePath, List<String> ligandFilePaths) {
        List<Map<String, Object>> interactionResults = new ArrayList<>();
      //  List<String>sampleLigans = new ArrayList<>(Arrays.asList(ligandFilePaths.get(0)));
        List<Interaction>interactionList = new ArrayList<>();
        try {
            Structure proteinStructure = StructureIO.getStructure(proteinFilePath);


            for (String ligandFilePath : ligandFilePaths) {
                Structure ligandStructure = StructureIO.getStructure(ligandFilePath);

                for (Chain proteinChain : proteinStructure.getChains()) {
                    for (Chain ligandChain : ligandStructure.getChains()) {
                        AtomContactSet contacts = StructureTools.getAtomsInContact(proteinChain, ligandChain, 4.0, true);

                        for (AtomContact contact : contacts) {
                            Atom atom1 = contact.getPair().getFirst();
                            Atom atom2 = contact.getPair().getSecond();
                            double distance = contact.getDistance();

                            // Getting coordinates
                            double[] coords1 = atom1.getCoords();
                            double[] coords2 = atom2.getCoords();


                            Interaction interaction = new Interaction();
                            interaction.setAtom1(atom1.getName());
                            interaction.setResNum1(atom1.getGroup().getResidueNumber().getSeqNum());
                            interaction.setCoords1(atom1.getCoords());
                            interaction.setAtom2(atom2.getName());
                            interaction.setResNum2(atom2.getGroup().getResidueNumber().getSeqNum());
                            interaction.setCoords2(atom2.getCoords());
                            interaction.setDistance(contact.getDistance());

                            if (distance <= 3.0) {
                                interaction.setType("hydrogenBond");
                            } else if (distance > 3.0 && distance <= 4.0) {
                                interaction.setType("hydrophobic");
                            } else {
                                interaction.setType("ionic");
                            }


                            interactionList.add(interaction);

                            // Determine interaction type
                            //String interactionType = determineInteractionType(atom1, atom2, distance);
                           // interactionResult.put("type", interactionType);


                          //  interactionResults.add(interactionResult);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interactionList;
    }
//using gemini
*/
    public List<Interaction> analyzeInteractions(String proteinFilePath, List<String> ligandFilePaths) {
        List<Interaction>interactionList = new ArrayList<>();
        try {
            Structure proteinStructure = StructureIO.getStructure(proteinFilePath);

            for (String ligandFilePath : ligandFilePaths) {
                Structure ligandStructure = StructureIO.getStructure(ligandFilePath);

                for (Chain proteinChain : proteinStructure.getChains()) {
                    for (Chain ligandChain : ligandStructure.getChains()) {
                        AtomContactSet contacts = StructureTools.getAtomsInContact(proteinChain, ligandChain, 4.0, true);

                        for (AtomContact contact : contacts) {
                            Interaction interaction = createInteractionFromContact(contact);
                            interactionList.add(interaction);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Improve error handling (logging, etc.)
            e.printStackTrace();
        }
        return interactionList;
    }
    private Interaction createInteractionFromContact(AtomContact contact) {
        Atom atom1 = contact.getPair().getFirst();
        Atom atom2 = contact.getPair().getSecond();
        double distance = contact.getDistance();

        // Create Interaction object
        Interaction interaction = new Interaction();

        // Atom Information
        interaction.setAtom1(atom1.getName());
        interaction.setResNum1(atom1.getGroup().getResidueNumber().getSeqNum());
        interaction.setCoords1(atom1.getCoords());

        interaction.setAtom2(atom2.getName());
        interaction.setResNum2(atom2.getGroup().getResidueNumber().getSeqNum());
        interaction.setCoords2(atom2.getCoords());

        // Chain IDs
        interaction.setChainId1(atom1.getGroup().getChain().getId());
        interaction.setChainId2(atom2.getGroup().getChain().getId());

        // Distance and Type
        interaction.setDistance(distance);
        interaction.setType(determineInteractionType(atom1, atom2, distance));

        return interaction;
    }
    private InteractionType determineInteractionType(Atom atom1, Atom atom2, double distance) {
        if (isHydrogenBond(atom1, atom2, distance)) {
            return InteractionType.HYDROGEN_BOND;
        } else if (isHydrophobic(atom1, atom2, distance)) {
            return InteractionType.HYDROPHOBIC;
        } else if (isSaltBridge(atom1, atom2, distance)) {
            return InteractionType.SALT_BRIDGE;
        }
        else if(isIonicInteraction(atom1, atom2, distance)){
            return InteractionType.IONIC;
        }
        /*else if (isPiStacking(atom1, atom2, distance)) {
            return InteractionType.PI_STACKING;
        } */else {
            return InteractionType.OTHER;
        }
    }
    private boolean isHydrogenBond1(Atom atom1, Atom atom2, double distance) {
        // 1. Check if distance is within hydrogen bond range
        if (distance > 3.5) {
           return false;
        }
        String element1 = atom1.getElement().toString();
        String element2 = atom2.getElement().toString();

      //  System.out.println("Hydrogen Bond Check:");
      //  System.out.println("  Atom 1: " + element1 + ", Atom 2: " + element2);
      //  System.out.println("  Distance: " + distance);

        boolean elementCheck = (element1.equals("O") || element1.equals("N")) &&
                (element2.equals("O") || element2.equals("N"));

       // System.out.println("  Element Check Result: " + elementCheck);
        return elementCheck; // Update the return value
    }
    private boolean isHydrophobic1(Atom atom1, Atom atom2, double distance) {
        // Typical hydrophobic atoms
        String[] hydrophobicElements = {"C"}; // You can expand this list
        String element1 = atom1.getElement().toString();
        String element2 = atom2.getElement().toString();

        // Check if both atoms are hydrophobic and within distance cutoff
        return (Arrays.asList(hydrophobicElements).contains(element1) &&
                Arrays.asList(hydrophobicElements).contains(element2) &&
                distance <= 4.5); // Adjust distance cutoff as needed
    }

    private boolean isSaltBridge1(Atom atom1, Atom atom2, double distance) {
        if(!(distance <= 4.0)){
            return false;
        }
        // Customizable charged residue definitions (adjust as needed)
        String[] positiveResidues = {"ARG", "LYS", "HIS"};  // Histidine can be both +ve or -ve
        String[] negativeResidues = {"ASP", "GLU"};

        // Extract residue names (Method might vary based on your BioJava version)
        String residueName1 = extractResidueName(atom1);
        String residueName2 = extractResidueName(atom2);

        // Check if residues are charged
        boolean isResidue1Charged = Arrays.asList(positiveResidues).contains(residueName1) ||
                Arrays.asList(negativeResidues).contains(residueName1);
        boolean isResidue2Charged = Arrays.asList(positiveResidues).contains(residueName2) ||
                Arrays.asList(negativeResidues).contains(residueName2);

        // Check for opposite charges (allow either residue to be charged)
        boolean oppositeCharges = (isResidue1Charged && !isResidue2Charged) ||
                (!isResidue1Charged && isResidue2Charged);

        // Apply distance cutoff
        return isResidue1Charged && isResidue2Charged && oppositeCharges && distance <= 4.0;
    }

    private String extractResidueName(Atom atom) {
        String residueName = atom.getGroup().getPDBName();

        if (residueName == null || residueName.isEmpty()) {
            return "RESIDUE_EXTRACTION_ERROR";
        }

        return residueName;
    }

    //finding isIonicInteraction START
    private boolean isIonicInteraction(Atom atom1, Atom atom2, double distance) {
        if (!(distance <= 5.0)) { // Adjust distance cutoff as needed
            return false;
        }

        // Customizable charged residue/group definitions
        String[] positiveResidues = {"ARG", "LYS", "HIS"};
        String[] negativeResidues = {"ASP", "GLU"};
        String[] otherPotentialIons = {"NA", "CL", "MG", "ZN", /* Add more if relevant */ };

        // Extract names - adjust as needed for your BioJava version
        String name1 = extractName(atom1);
        String name2 = extractName(atom2);

        // 1. Classic Salt Bridge
        boolean isSaltBridge = (Arrays.asList(positiveResidues).contains(name1) && Arrays.asList(negativeResidues).contains(name2)) ||
                (Arrays.asList(negativeResidues).contains(name1) && Arrays.asList(positiveResidues).contains(name2));

        // 2. Interactions with ions
        boolean involvesIon = Arrays.asList(otherPotentialIons).contains(name1) || Arrays.asList(otherPotentialIons).contains(name2);

        // 3. (Optional) Charged residue with polar group
        boolean chargedWithPolar = (Arrays.asList(positiveResidues).contains(name1) || Arrays.asList(negativeResidues).contains(name1)) &&
                (isPolar(atom2) || isPolar(atom1));

        return isSaltBridge || involvesIon || chargedWithPolar;
    }

    // Helper function (adjust the logic to suit your polar group definition)
    private boolean isPolar(Atom atom) {
        String element = atom.getElement().toString();
        return element.equals("O") || element.equals("N"); // Add more elements if needed
    }

    // You'll likely need to implement this based on your BioJava methods
    private String extractName(Atom atom) {
        // Example:
        return atom.getGroup().getPDBName();
    }

//code from chatGPT
private boolean isHydrogenBond(Atom atom1, Atom atom2, double distance) {
    // Assuming distance is already calculated and passed to this method

    // 1. Distance check for hydrogen bond range
    if (distance > 3.5) {
        return false;
    }

    // Determine if either atom is a hydrogen donor (covalently bonded to H)
    boolean isDonor1 = isHydrogenDonor(atom1);
    boolean isDonor2 = isHydrogenDonor(atom2);

    // Check if one atom is a donor and the other is an acceptor (O or N, not covalently bonded to H)
    if (!((isDonor1 || isDonor2) && (isAcceptor(atom1) || isAcceptor(atom2)))) {
        return false;
    }

    // Assuming we have a method to find the hydrogen atom bonded to the donor
    Atom hydrogenAtom = findBondedHydrogen(isDonor1 ? atom1 : atom2);
    if (hydrogenAtom == null) {
        // If no bonded hydrogen atom could be found, return false as we can't form a hydrogen bond
        return false;
    }

    // Proceed with angle calculation only if hydrogenAtom is not null
    double angle = calculateAngle(isDonor1 ? atom1 : hydrogenAtom, hydrogenAtom, isDonor1 ? atom2 : atom1);

    // Check for typical hydrogen bond angle range (usually > 120 degrees)
    if (angle < 120.0) {
        return false;
    }

    return true;
}

    private boolean isHydrogenDonor(Atom atom) {
        // Assuming we can get the Group (e.g., amino acid or nucleotide) to which the atom belongs
        Group group = atom.getGroup();

        // Iterate through all atoms in the group to find bonded atoms
        for (Atom bondedAtom : group.getAtoms()) {
            // Check if there is a bond between 'atom' and 'bondedAtom' and if 'bondedAtom' is hydrogen
            if (isBonded(atom, bondedAtom) && bondedAtom.getElement().toString().equals("H")) {
                return true; // Found a hydrogen bonded to the given atom
            }
        }

        return false; // No hydrogen atoms bonded to the given atom
    }
    private boolean isBonded(Atom atom1, Atom atom2) {
        // This implementation assumes that you need to work directly with the group or structure
        // to infer bonding information, typically through distance measurements or explicit bond records
        // when direct bond information is not straightforwardly available.

        Group group = atom1.getGroup();

        // Check if both atoms belong to the same group
        if (!atom2.getGroup().equals(group)) {
            return false;
        }

        // If your version of BioJava directly supports accessing bonds within groups or structures, use that approach here
        // For example, if there's a method to get all bonds in a group (not typical in standard BioJava models for amino acids or nucleotides)
        // you would iterate over those bonds and check if atom1 and atom2 are connected.

        // Fallback: Use a distance-based heuristic for covalent bonding, acknowledging its limitations
        final double covalentBondMaxDistance = 1.6; // A generic threshold, best adjusted per atom types
        double distance = calculateDistanceBetweenAtoms(atom1, atom2);

        return distance <= covalentBondMaxDistance;
    }
    private double calculateDistanceBetweenAtoms(Atom atom1, Atom atom2) {
        double xDiff = atom1.getX() - atom2.getX();
        double yDiff = atom1.getY() - atom2.getY(); // Corrected variable name here
        double zDiff = atom1.getZ() - atom2.getZ();

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff); // Also corrected here
    }
        private boolean isAcceptor(Atom atom) {
        // Check if the atom is an oxygen or nitrogen and not covalently bonded to hydrogen
        String element = atom.getElement().toString();
        return (element.equals("O") || element.equals("N")) && !isHydrogenDonor(atom);
    }

    private Atom findBondedHydrogen(Atom donor) {
        Group group = donor.getGroup();
        double covalentBondMaxDistanceForHydrogen = 1.2; // A typical covalent bond length involving hydrogen

        // Iterate through all atoms in the group to find hydrogen atoms
        for (Atom atom : group.getAtoms()) {
            // Check if the current atom is hydrogen
            if ("H".equalsIgnoreCase(atom.getElement().toString())) {
                // Calculate the distance between the donor and the hydrogen atom
                double distance = calculateDistanceBetweenAtoms(donor, atom);

                // Check if the distance suggests a covalent bond
                if (distance <= covalentBondMaxDistanceForHydrogen) {
                    return atom; // Return the first hydrogen atom that appears to be covalently bonded
                }
            }
        }

        return null; // No covalently bonded hydrogen atom found
    }

    private double calculateAngle(Atom atom1, Atom atom2, Atom atom3) {
        // Convert Atom positions to vectors
        double[] vec1 = {atom1.getX() - atom2.getX(), atom1.getY() - atom2.getY(), atom1.getZ() - atom2.getZ()};
        double[] vec2 = {atom3.getX() - atom2.getX(), atom3.getY() - atom2.getY(), atom3.getZ() - atom2.getZ()};

        // Calculate dot product of vec1 and vec2
        double dotProduct = vec1[0] * vec2[0] + vec1[1] * vec2[1] + vec1[2] * vec2[2];

        // Calculate magnitudes of vec1 and vec2
        double magnitudeVec1 = Math.sqrt(vec1[0] * vec1[0] + vec1[1] * vec1[1] + vec1[2] * vec1[2]);
        double magnitudeVec2 = Math.sqrt(vec2[0] * vec2[0] + vec2[1] * vec2[1] + vec2[2] * vec2[2]);

        // Calculate the cosine of the angle between vec1 and vec2
        double cosTheta = dotProduct / (magnitudeVec1 * magnitudeVec2);

        // Calculate the angle in radians, and then convert to degrees
        double angleRadians = Math.acos(cosTheta);

        // Convert radians to degrees
        double angleDegrees = Math.toDegrees(angleRadians);

        return angleDegrees;
    }
    private boolean isHydrophobic(Atom atom1, Atom atom2, double distance) {
        // Assume a method exists to check if an atom belongs to a nonpolar amino acid
        boolean nonpolar1 = isNonpolarGroupAtom(atom1);
        boolean nonpolar2 = isNonpolarGroupAtom(atom2);

        // Check if both atoms are nonpolar and within a reasonable distance to interact hydrophobically
        // The distance threshold for hydrophobic interactions is less strict and can vary;
        // you might need to adjust this based on your model or literature
      //  System.out.println("Nonpolar1: " + nonpolar1 + ", Nonpolar2: " + nonpolar2 + ", Distance: " + distance);
        return nonpolar1 && nonpolar2 && distance <= 5.0; // Example distance threshold
    }
    private boolean isNonpolarGroupAtom(Atom atom) {
        Group group = atom.getGroup();
       // System.out.println("Atom Name: " + atom.getName() + ", Group Class: " + group.getClass().getSimpleName());
        String element = atom.getElement().toString().toUpperCase();
        if ("C".equals(element)) {
            return true;
        }


        if (group instanceof AminoAcid) {
            AminoAcid aminoAcid = (AminoAcid) group;
            String threeLetterCode = aminoAcid.getChemComp().getThreeLetterCode().toUpperCase();
          //  System.out.println("Amino Acid Code: " + threeLetterCode); // Debugging statement

            List<String> nonpolarAminoAcids = Arrays.asList("ALA", "ILE", "LEU", "MET", "PHE", "PRO", "TRP", "VAL", "CYS");
            boolean isNonpolar = nonpolarAminoAcids.contains(threeLetterCode);
          //  System.out.println("Is Nonpolar: " + isNonpolar); // Debugging statement
            return isNonpolar;
        }
        return false;
    }

    private boolean isSaltBridge(Atom atom1, Atom atom2, double distance) {
        // Check if the atoms are within the distance criteria for a salt bridge.
        if (distance <= 6.0) {
            boolean atom1Charged = isCharged(atom1);
            boolean atom2Charged = isCharged(atom2);
            System.out.println("atom1Charged: " + atom1Charged + ", atom2Charged: " + atom2Charged);


            if (!atom1Charged || !atom2Charged) {
                // If either atom isn't charged, they can't form a salt bridge.
                return false;
            }
            // Determine if each atom belongs to an acidic or basic group.
            boolean atom1Acidic = isAcidic(atom1);
            boolean atom2Acidic = isAcidic(atom2);
            boolean atom1Basic = isBasic(atom1);
            boolean atom2Basic = isBasic(atom2);

          //  System.out.println("atom1Acidic: " + atom1Acidic + ", atom2Acidic: " + atom2Acidic);
          //  System.out.println("atom1Basic: " + atom1Basic + ", atom2Basic: " + atom2Basic);


            // Check for complementary acidic and basic pairings.
            boolean hasAcidBasePair = (atom1Acidic && atom2Basic) || (atom1Basic && atom2Acidic);

            // Print debug information to help diagnose matching issues.
            System.out.println("Acid/Base Pairing: " + hasAcidBasePair + ", Distance: " + distance);

            // Return true if there's an acidic-basic pair within the appropriate distance.
            return hasAcidBasePair;
        }

        // If the distance exceeds the threshold, or no acid-base pairing is found, return false.
        return false;
    }

    private boolean isCharged(Atom atom) {
        // Quickly return true for explicitly charged atoms.
        if (atom.getCharge() != 0) {
            return true;
        }

        Group group = atom.getGroup();

        // Check if the atom is part of a standard amino acid with a known charge.
        List<String> CHARGED_AMINO_ACIDS = Arrays.asList("ASP", "GLU", "LYS", "ARG", "HIS");
        if (group instanceof AminoAcid) {
            AminoAcid aminoAcid = (AminoAcid) group;
            String threeLetterCode = aminoAcid.getChemComp().getThreeLetterCode().toUpperCase();
            return CHARGED_AMINO_ACIDS.contains(threeLetterCode);
        }

        // Check for specific non-amino acid groups known to be charged, e.g., phosphate groups.
        if (group instanceof HetatomImpl && "phosphate".equals(group.getChemComp().getType())) {
            return true;
        }
        return false; // Return false if none of the conditions for being charged are met.
    }


    /**
     * Determines if an atom is part of an acidic amino acid group.
     */
    private boolean isAcidic(Atom atom) {
        Group group = atom.getGroup();
        if (group instanceof AminoAcid) {
            AminoAcid aminoAcid = (AminoAcid) group;
            String threeLetterCode = aminoAcid.getChemComp().getThreeLetterCode().toUpperCase();
            // List of acidic amino acids
            List<String> acidicAminoAcids = Arrays.asList("ASP", "GLU");
            return acidicAminoAcids.contains(threeLetterCode);
        }
        return false;
    }

    /**
     * Determines if an atom is part of a basic amino acid group.
     */
    private boolean isBasic(Atom atom) {
        Group group = atom.getGroup();
        if (group instanceof AminoAcid) {
            AminoAcid aminoAcid = (AminoAcid) group;
            String threeLetterCode = aminoAcid.getChemComp().getThreeLetterCode().toUpperCase();
            // List of basic amino acids
            List<String> basicAminoAcids = Arrays.asList("LYS", "ARG", "HIS");
            return basicAminoAcids.contains(threeLetterCode);
        }
        return false;
    }
}
