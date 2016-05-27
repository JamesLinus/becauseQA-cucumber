/**
 */
package org.becausecucumber.eclipse.plugin.cucumber;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario Outline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getTags <em>Tags</em>}</li>
 *   <li>{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getTitle <em>Title</em>}</li>
 *   <li>{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getNarrative <em>Narrative</em>}</li>
 *   <li>{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getSteps <em>Steps</em>}</li>
 *   <li>{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getExamples <em>Examples</em>}</li>
 * </ul>
 *
 * @see org.becausecucumber.eclipse.plugin.cucumber.CucumberPackage#getScenarioOutline()
 * @model
 * @generated
 */
public interface ScenarioOutline extends EObject
{
  /**
   * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
   * The list contents are of type {@link org.becausecucumber.eclipse.plugin.cucumber.Tag}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tags</em>' containment reference list.
   * @see org.becausecucumber.eclipse.plugin.cucumber.CucumberPackage#getScenarioOutline_Tags()
   * @model containment="true"
   * @generated
   */
  EList<Tag> getTags();

  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Title</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see org.becausecucumber.eclipse.plugin.cucumber.CucumberPackage#getScenarioOutline_Title()
   * @model
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Narrative</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Narrative</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Narrative</em>' attribute.
   * @see #setNarrative(String)
   * @see org.becausecucumber.eclipse.plugin.cucumber.CucumberPackage#getScenarioOutline_Narrative()
   * @model
   * @generated
   */
  String getNarrative();

  /**
   * Sets the value of the '{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getNarrative <em>Narrative</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Narrative</em>' attribute.
   * @see #getNarrative()
   * @generated
   */
  void setNarrative(String value);

  /**
   * Returns the value of the '<em><b>Steps</b></em>' containment reference list.
   * The list contents are of type {@link org.becausecucumber.eclipse.plugin.cucumber.Step}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Steps</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Steps</em>' containment reference list.
   * @see org.becausecucumber.eclipse.plugin.cucumber.CucumberPackage#getScenarioOutline_Steps()
   * @model containment="true"
   * @generated
   */
  EList<Step> getSteps();

  /**
   * Returns the value of the '<em><b>Examples</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Examples</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Examples</em>' containment reference.
   * @see #setExamples(Examples)
   * @see org.becausecucumber.eclipse.plugin.cucumber.CucumberPackage#getScenarioOutline_Examples()
   * @model containment="true"
   * @generated
   */
  Examples getExamples();

  /**
   * Sets the value of the '{@link org.becausecucumber.eclipse.plugin.cucumber.ScenarioOutline#getExamples <em>Examples</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Examples</em>' containment reference.
   * @see #getExamples()
   * @generated
   */
  void setExamples(Examples value);

} // ScenarioOutline
