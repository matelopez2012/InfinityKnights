/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Models.Nebula;
import Models.Planet;
import Models.PlanetarySystem;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author matel
 */
public class LoaderJSON
{

    public LoaderJSON()
    {
    }

    public LinkedList<Nebula> readJSON(String filePath)
    {
        JOptionPane.showMessageDialog(null, "El re");
        LinkedList<Nebula> resNebulas = new LinkedList<>();
        CAFDataEx dataReader = CAFDataEx.dataWithContentsOfFile(filePath);
        JSONObject nebulas = dataReader.toJSONObject().getJSONObject("NEBULAS");
        for (String nebulaName : nebulas.keySet())//Todas la nebulas -- contenido de Mateo
        {
            JSONObject infoNebula = nebulas.getJSONObject(nebulaName);//Position y planetarySystems
            Object objNPosition = infoNebula.get("POSITION");//Posicion de cada nebula
            JSONArray nPosition = (JSONArray) objNPosition;
            Nebula newNebula = new Nebula(nebulaName, nPosition.getDouble(0), nPosition.getDouble(1));
            for (String planetarySystemName : infoNebula.keySet())//Position, CAFDataEx , jimmy
            {
                if (!planetarySystemName.equalsIgnoreCase("POSITION"))
                {
                    JSONObject infoPlanetarySystem = infoNebula.getJSONObject(planetarySystemName);
                    Object objPSPosition = infoPlanetarySystem.getJSONObject("POSITION");//Posicion de cada sistema planetario
                    JSONArray psPosition = (JSONArray) objPSPosition;
                    PlanetarySystem newPlanetarySystem = new PlanetarySystem(planetarySystemName, psPosition.getDouble(0), psPosition.getDouble(1));

                    for (String planetName : infoPlanetarySystem.keySet())//Position, victor
                    {
                        JSONObject infoPlanet = infoPlanetarySystem.getJSONObject(planetName);
                        Object objPPosition = infoPlanet.getJSONObject("POSITION");//Posicion de cada planeta
                        JSONArray pPosition = (JSONArray) objPPosition;
                        Planet newPlanet = new Planet(planetName, pPosition.getDouble(0), pPosition.getDouble(1));
                        newPlanet.setElementZero(infoPlanet.getInt("ZERO"));
                        newPlanet.setPlatinium(infoPlanet.getInt("PLATINO"));
                        newPlanet.setIridium(infoPlanet.getInt("IRIDIO"));
                        newPlanet.setPaladium(infoPlanet.getInt("PALADIO"));
                        newPlanetarySystem.add(newPlanet);
                    }
                    newNebula.add(newPlanetarySystem);
                }
            }
            resNebulas.add(newNebula);
        }
        JOptionPane.showMessageDialog(null, resNebulas.toString());
        return resNebulas;
    }
}
