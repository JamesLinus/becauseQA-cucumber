/**
 */
package org.becausecucumber.eclipse.plugin.cucumber.impl;

import org.becausecucumber.eclipse.plugin.cucumber.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CucumberFactoryImpl extends EFactoryImpl implements CucumberFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CucumberFactory init()
  {
    try
    {
      CucumberFactory theCucumberFactory = (CucumberFactory)EPackage.Registry.INSTANCE.getEFactory(CucumberPackage.eNS_URI);
      if (theCucumberFactory != null)
      {
        return theCucumberFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CucumberFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CucumberFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case CucumberPackage.FEATURE: return createFeature();
      case CucumberPackage.BACKGROUND: return createBackground();
      case CucumberPackage.SCENARIO: return createScenario();
      case CucumberPackage.SCENARIO_OUTLINE: return createScenarioOutline();
      case CucumberPackage.STEP: return createStep();
      case CucumberPackage.EXAMPLES: return createExamples();
      case CucumberPackage.TABLE: return createTable();
      case CucumberPackage.DOC_STRING: return createDocString();
      case CucumberPackage.TAG: return createTag();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature createFeature()
  {
    FeatureImpl feature = new FeatureImpl();
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Background createBackground()
  {
    BackgroundImpl background = new BackgroundImpl();
    return background;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Scenario createScenario()
  {
    ScenarioImpl scenario = new ScenarioImpl();
    return scenario;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScenarioOutline createScenarioOutline()
  {
    ScenarioOutlineImpl scenarioOutline = new ScenarioOutlineImpl();
    return scenarioOutline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Step createStep()
  {
    StepImpl step = new StepImpl();
    return step;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Examples createExamples()
  {
    ExamplesImpl examples = new ExamplesImpl();
    return examples;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Table createTable()
  {
    TableImpl table = new TableImpl();
    return table;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DocString createDocString()
  {
    DocStringImpl docString = new DocStringImpl();
    return docString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Tag createTag()
  {
    TagImpl tag = new TagImpl();
    return tag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CucumberPackage getCucumberPackage()
  {
    return (CucumberPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static CucumberPackage getPackage()
  {
    return CucumberPackage.eINSTANCE;
  }

} //CucumberFactoryImpl
